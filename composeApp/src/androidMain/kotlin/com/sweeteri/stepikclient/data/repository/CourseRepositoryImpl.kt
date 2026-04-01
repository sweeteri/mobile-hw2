package com.sweeteri.stepikclient.data.repository

import com.sweeteri.stepikclient.data.local.mapper.toDomain
import com.sweeteri.stepikclient.data.local.model.CourseDetail
import com.sweeteri.stepikclient.data.remote.api.StepikApiClient

class CourseDetailRepositoryImpl(
    private val apiClient: StepikApiClient
) : CourseDetailRepository {

    override suspend fun getCourseDetail(courseId: Int): Result<CourseDetail> {
        return try {
            val courseDto = apiClient.getCourse(courseId)

            val sections = apiClient.getSections(courseDto.sections)

            val units = apiClient.getUnits(
                sections.flatMap { it.units }
            )

            val lessonIds = units.mapNotNull { it.lessonId }

            val lessons = apiClient.getLessons(lessonIds)

            val reviewSummary = apiClient.getCourseReviewSummary(courseId)

            val course = courseDto.toDomain(
                sections,
                units,
                lessons,
                reviewSummary
            )

            Result.success(course)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun enroll(courseId: Int, userId: Int): Result<Boolean> {
        return try {
            val success = apiClient.enrollCourse(courseId, userId)
            Result.success(success)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}