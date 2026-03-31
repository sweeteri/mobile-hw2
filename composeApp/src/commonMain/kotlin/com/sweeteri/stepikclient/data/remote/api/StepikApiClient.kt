package com.sweeteri.stepikclient.data.remote.api

import com.sweeteri.stepikclient.data.remote.client.NetworkClient
import com.sweeteri.stepikclient.data.remote.dto.CourseDetailDto
import com.sweeteri.stepikclient.data.remote.dto.CourseDto
import com.sweeteri.stepikclient.data.remote.dto.CourseReviewSummaryDto
import com.sweeteri.stepikclient.data.remote.dto.LessonDto
import com.sweeteri.stepikclient.data.remote.dto.LessonsResponse
import com.sweeteri.stepikclient.data.remote.dto.MetaDto
import com.sweeteri.stepikclient.data.remote.dto.SectionDto
import com.sweeteri.stepikclient.data.remote.dto.SectionsResponse
import com.sweeteri.stepikclient.data.remote.dto.StepikResponse
import com.sweeteri.stepikclient.data.remote.dto.UnitDto
import com.sweeteri.stepikclient.data.remote.dto.UnitsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class StepikApiClient(private val client: HttpClient = NetworkClient.httpClient) {
    suspend fun getCourses(page: Int, query: String = ""): StepikResponse<CourseDto> {
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

    suspend fun getCourseReviews(summaryIds: List<Int>): StepikResponse<CourseDto> {
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

    suspend fun getCourse(courseId: Int): CourseDetailDto {
        val response: StepikResponse<CourseDetailDto> = client
            .get("https://stepik.org/api/courses/$courseId")
            .body()

        return response.courses.first()
    }

    suspend fun getSections(sectionIds: List<Int>): List<SectionDto> {
        if (sectionIds.isEmpty()) return emptyList()

        val response: SectionsResponse = client.get("https://stepik.org/api/sections") {
            sectionIds.forEach { parameter("ids[]", it) }
        }.body()

        return response.sections
    }

    suspend fun getUnits(unitIds: List<Int>): List<UnitDto> {
        if (unitIds.isEmpty()) return emptyList()

        val response: UnitsResponse = client.get("https://stepik.org/api/units") {
            unitIds.forEach { parameter("ids[]", it) }
        }.body()

        return response.units
    }

    suspend fun getLessons(lessonIds: List<Int>, chunkSize: Int = 50): List<LessonDto> {
        if (lessonIds.isEmpty()) return emptyList()

        val allLessons = mutableListOf<LessonDto>()

        lessonIds.chunked(chunkSize).forEach { chunk ->
            try {
                val response: LessonsResponse = client.get("https://stepik.org/api/lessons") {
                    chunk.forEach { parameter("ids[]", it) }
                }.body()
                allLessons.addAll(response.lessons)
            } catch (e: Exception) {
                println("Failed to fetch lessons chunk: ${chunk.take(5)}... (${chunk.size} total). Error: $e")
            }
        }

        return allLessons
    }

    suspend fun getCourseReviewSummary(courseId: Int): CourseReviewSummaryDto? {
        return try {
            val response: HttpResponse = client.get(
                "https://stepik.org/api/course-review-summaries/$courseId"
            )

            if (!response.status.isSuccess()) {
                return null
            }

            response.body()

        } catch (e: Exception) {
            null
        }
    }

    suspend fun enrollCourse(courseId: Int, userId: Int): Boolean {
        val response: HttpResponse = client.post("https://stepik.org/api/enrollments") {
            contentType(ContentType.Application.Json)  // <-- здесь
            setBody(mapOf("course" to courseId, "user" to userId))
        }
        return response.status.isSuccess()
    }
}