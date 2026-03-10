package com.example.mobile_hw2.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StepikResponse(
    val meta: MetaDto,
    val courses: List<CourseDto> = emptyList(),
    @SerialName("course-review-summaries")
    val summaries: List<ReviewSummaryDto> = emptyList()
)

@Serializable
data class MetaDto(
    val page: Int,
    @SerialName("has_next") val hasNext: Boolean,
    @SerialName("has_previous") val hasPrevious: Boolean
)

@Serializable
data class CourseDto(
    val id: Int,
    val title: String,
    val summary: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("review_summary") val reviewSummaryId: Int? = null,
    @SerialName("learners_count") val learnersCount: Int? = 0,
    @SerialName("total_units") val totalUnits: Int? = 0,
    var average: Double = 0.0
)


@Serializable
data class ReviewSummaryDto(
    val id: Int,
    val course: Int,
    val average: Double
)
