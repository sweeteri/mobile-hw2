package com.example.mobile_hw2.data.repository

import com.example.mobile_hw2.data.model.StepikResponse
import com.example.mobile_hw2.data.remote.NetworkClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class CoursesRepository {
    private val client = NetworkClient.httpClient

    suspend fun getCourses(page: Int, query: String = ""): Result<StepikResponse> {
        return try {
            val response: StepikResponse = client.get("https://stepik.org/api/courses") {
                parameter("page", page)
                if (query.isNotEmpty()) {
                    parameter("search", query)
                } else {
                    parameter("is_public", "true")
                    parameter("order", "-activity")
                }
            }.body()

            val courses = response.courses

            if (courses.isEmpty()) return Result.success(response)

            val summaryIds = courses.mapNotNull { it.reviewSummaryId }

            if (summaryIds.isEmpty()) return Result.success(response)

            val reviewsResponse: StepikResponse =
                client.get("https://stepik.org/api/course-review-summaries") {
                    summaryIds.forEach { id ->
                        parameter("ids[]", id)
                    }
                }.body()

            val ratingMap = reviewsResponse.summaries.associate { it.id to it.average }

            val updatedCourses = courses.map { course ->
                course.copy(average = ratingMap[course.reviewSummaryId] ?: 0.0)
            }

            Result.success(response.copy(courses = updatedCourses))

        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
