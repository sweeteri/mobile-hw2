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
            val response: StepikResponse = client
                .get("https://stepik.org/api/courses") {
                    parameter("page", page)
                    if (query.isNotEmpty()) {
                        parameter("search", query)
                    } else {
                        parameter("is_public", "true")
                        parameter("order", "-activity")
                    }
                }.body()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}