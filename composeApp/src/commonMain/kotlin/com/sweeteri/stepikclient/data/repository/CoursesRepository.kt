package com.sweeteri.stepikclient.data.repository

import com.sweeteri.stepikclient.data.model.StepikResponse
import com.sweeteri.stepikclient.data.remote.StepikApiClient
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CancellationException

class CoursesRepository(
    private val apiClient: StepikApiClient = StepikApiClient()
) {

    suspend fun getCourses(page: Int, query: String = ""): Result<StepikResponse> {
        return try {
            val response = apiClient.getCourses(page, query)
            val courses = response.courses

            if (courses.isEmpty()) {
                return Result.success(response)
            }


            val summaryIds = courses.mapNotNull { it.reviewSummaryId }

            if (summaryIds.isEmpty()) {
                val coursesWithZeroRating = courses.map { course ->
                    course.copy(average = 0.0)
                }
                return Result.success(response.copy(courses = coursesWithZeroRating))
            }

            val reviewsResponse = apiClient.getCourseReviews(summaryIds)
            val ratingMap = reviewsResponse.summaries.associate { it.id to it.average }

            val updatedCourses = courses.map { course ->
                val newRating = if (course.reviewSummaryId != null) {
                    ratingMap[course.reviewSummaryId] ?: 0.0
                } else {
                    0.0
                }
                course.copy(average = newRating)
            }
            Result.success(response.copy(courses = updatedCourses))

        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Napier.e("Loading courses error ", e)
            Result.failure(e)
        }
    }
}
