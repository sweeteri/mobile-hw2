package com.sweeteri.stepikclient.data.repository

import com.sweeteri.stepikclient.data.local.CourseDao
import com.sweeteri.stepikclient.data.local.toDomain
import com.sweeteri.stepikclient.data.local.toEntity
import com.sweeteri.stepikclient.data.model.Course
import com.sweeteri.stepikclient.data.remote.StepikApiClient
import com.sweeteri.stepikclient.data.remote.toDomain
import io.github.aakira.napier.Napier
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.round

class CoursesRepositoryImpl(
    private val courseDao: CourseDao,
    private val apiClient: StepikApiClient
) : CoursesRepository {


    override suspend fun getCourses(page: Int, query: String): Result<List<Course>> {
        return try {
            val response = apiClient.getCourses(page, query)
            val courses = response.courses

            val summaryIds = courses.mapNotNull { it.reviewSummaryId }
            val ratingMap = if (summaryIds.isNotEmpty()) {
                val reviewsResponse = apiClient.getCourseReviews(summaryIds)
                reviewsResponse.summaries.associate { it.id to it.average }
            } else emptyMap()

            val updatedCourses = courses.map { dto ->
                val newAverage = dto.reviewSummaryId?.let { ratingMap[it] } ?: 0.0
                val roundedAverage = round(newAverage * 10) / 10.0
                dto.copy(average = roundedAverage).toDomain()
            }

            courseDao.insertAll(updatedCourses.map { it.toEntity() })

            Result.success(updatedCourses)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Napier.e("Loading courses failed", e)
            val cached = courseDao.getAll().map { it.toDomain() }
            if (cached.isNotEmpty()) Result.success(cached)
            else Result.failure(e)
        }
    }
}
