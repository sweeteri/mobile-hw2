package com.sweeteri.stepikclient.data.remote

import com.sweeteri.stepikclient.data.model.MetaDto
import com.sweeteri.stepikclient.data.model.StepikResponse
import com.sweeteri.stepikclient.data.repository.NetworkClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class StepikApiClient(private val client: HttpClient = NetworkClient.httpClient) {
    suspend fun getCourses(page: Int, query: String = ""): StepikResponse {
        return client.get("https://stepik.org/api/courses") {
            parameter("page", page)
            if (query.isNotEmpty()) {
                parameter("search", query)
            } else {
                parameter("is_public", "true")
                parameter("order", "-activity")
            }
        }.body()
    }

    suspend fun getCourseReviews(summaryIds: List<Int>): StepikResponse {
        return if (summaryIds.isEmpty()) {
            StepikResponse(
                meta = MetaDto(
                    page = 1,
                    hasNext = false,
                    hasPrevious = false,

                    ), courses = emptyList(), summaries = emptyList()
            )
        } else {
            client.get("https://stepik.org/api/course-review-summaries") {
                summaryIds.forEach { id ->
                    parameter("ids[]", id)
                }
            }.body()
        }
    }
}