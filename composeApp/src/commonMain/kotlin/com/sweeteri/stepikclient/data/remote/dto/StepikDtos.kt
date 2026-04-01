package com.sweeteri.stepikclient.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StepikResponse<T>(
    val meta: MetaDto? = null,
    val courses: List<T> = emptyList(),

    @SerialName("course-review-summaries")
    val summaries: List<ReviewSummaryDto> = emptyList()
)

@Serializable
data class SectionsResponse(
    val sections: List<SectionDto>
)

@Serializable
data class UnitsResponse(
    val units: List<UnitDto>
)

@Serializable
data class LessonsResponse(
    val lessons: List<LessonDto>
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

@Serializable
data class CourseDetailDto(
    val id: Int,
    val title: String,
    val summary: String? = null,
    val cover: String? = null,

    @SerialName("review_summary")
    val reviewSummaryId: Int? = null,

    @SerialName("learners_count")
    val learnersCount: Int? = 0,

    @SerialName("total_units")
    val totalUnits: Int? = 0,

    val sections: List<Int> = emptyList()
)

@Serializable
data class SectionDto(
    val id: Int,
    val title: String? = null,
    val units: List<Int> = emptyList()
)

@Serializable
data class UnitDto(
    val id: Int,

    @SerialName("lesson")
    val lessonId: Int? = null
)

@Serializable
data class LessonDto(
    val id: Int,
    val title: String? = null,
)

@Serializable
data class CourseReviewSummaryDto(
    val id: Int,
    val course: Int,
    val average: Double,
    @SerialName("review_count") val reviewCount: Int
)
