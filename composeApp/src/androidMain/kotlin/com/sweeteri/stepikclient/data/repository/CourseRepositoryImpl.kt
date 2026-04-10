package com.sweeteri.stepikclient.data.repository

import com.sweeteri.stepikclient.data.local.mapper.toDomain
import com.sweeteri.stepikclient.data.local.model.CourseDetail
import com.sweeteri.stepikclient.data.remote.api.StepikApiClient
import com.sweeteri.stepikclient.data.local.model.ReviewPage

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

            val lessonIds = units
                .mapNotNull { it.lessonId }
                .distinct()

            val lessons = apiClient.getLessons(lessonIds)

            val reviewSummary = courseDto.reviewSummaryId?.let {
                apiClient.getCourseReviewSummary(it)
            }
            val users = apiClient.getUsers(courseDto.authors)

            val course = courseDto.toDomain(
                sections,
                units,
                lessons,
                reviewSummary,
                users
            )



            Result.success(course)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun enroll(courseId: Int, userId: Int): Result<Boolean> {
        return Result.failure(NotImplementedError("Auth not implemented"))
    }


    override suspend fun getReviews(
        courseId: Int,
        page: Int,
        score: Int?
    ): Result<ReviewPage> {
        return try {

            val response = apiClient.getCourseReviewsPage(courseId, page)

            val userIds = response.courseReviews.map { it.user }.distinct()
            val users = apiClient.getUsers(userIds)
            val usersMap = users.associateBy { it.id }

            val reviews = response.courseReviews.map { dto ->
                val user = usersMap[dto.user]

                dto.toDomain(
                    userName = user?.fullName ?: "Аноним",
                    userAvatar = user?.avatar
                )
            }

            val distribution = reviews.groupingBy { it.score }.eachCount()
            val average = if (reviews.isNotEmpty())
                reviews.map { it.score }.average()
            else 0.0

            Result.success(
                ReviewPage(
                    reviews = reviews,
                    total = -1,
                    average = average,
                    distribution = distribution
                )
            )

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
