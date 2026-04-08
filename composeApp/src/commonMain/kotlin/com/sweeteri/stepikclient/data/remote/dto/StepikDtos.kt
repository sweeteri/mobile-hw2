package com.sweeteri.stepikclient.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StepikResponse<T>(
    val meta: MetaDto? = null,
    val courses: List<T> = emptyList(),

    @SerialName("course-review-summaries")
    val summaries: List<T> = emptyList()
)
@Serializable
data class StepikReviewSummaryDetailedResponse(
    val meta: MetaDto? = null,
    @SerialName("course-review-summaries")
    val summaries: List<CourseReviewSummaryDto> = emptyList()
)
@Serializable
data class StepikReviewSummaryResponse(
    val meta: MetaDto? = null,
    @SerialName("course-review-summaries")
    val summaries: List<ReviewSummaryDto> = emptyList()
)
@Serializable
data class StepikReviewSummaryShortResponse(
    val meta: MetaDto? = null,
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
    val description: String? = null,
    val cover: String? = null,

    @SerialName("review_summary")
    val reviewSummaryId: Int? = null,

    @SerialName("learners_count")
    val learnersCount: Int? = 0,

    @SerialName("total_units")
    val totalUnits: Int? = 0,

    val sections: List<Int> = emptyList(),
    @SerialName("authors")
    val authors: List<Int> = emptyList(),
    val requirements: String? = null,
    @SerialName("target_audience")
    val targetAudience: String? = null,
    @SerialName("acquired_skills")
    val acquiredSkills: List<String>? = null,

    @SerialName("quizzes_count")
    val quizzesCount: Int? = null,
    @SerialName("display_price")
    val displayPrice: String? = null,
    @SerialName("is_paid")
    val isPaid: Boolean? = null,
    @SerialName("time_to_complete")
    val timeToComplete: Int = 0,
    @SerialName("is_certificate_issued")
    val isCertificateIssued: Boolean? = null,

    @SerialName("certificate_regular_threshold")
    val certificateRegularThreshold: Int? = null,

    @SerialName("certificate_distinction_threshold")
    val certificateDistinctionThreshold: Int? = null,

    val difficulty: String? = null,

    @SerialName("learning_format")
    val learningFormat: String? = null,

    val language: String? = null,
    @SerialName("price")
    val price: Double? = null,

    @SerialName("currency_code")
    val currencyCode: String? = null,
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
    @SerialName("cover_url") val coverUrl: String? = null,
    @SerialName("time_to_complete") val timeToComplete: Int = 0,
    @SerialName("passed_by") val passedBy: Int = 0,
    @SerialName("vote_delta") val voteDelta: Int = 0
)

@Serializable
data class CourseReviewSummaryDto(
    val id: Int,
    val course: Int,
    val average: Double,
    @SerialName("count") val reviewCount: Int,
    val distribution: List<Int>
)

@Serializable
data class UsersResponse(
    val meta: MetaDto? = null,
    val users: List<UserDto> = emptyList()
)

@Serializable
data class UserDto(
    val id: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("avatar") val avatar: String? = null
) {
    val fullName: String
        get() = "$firstName $lastName"
}
@Serializable
data class CourseReviewsResponse(
    @SerialName("course-reviews")
    val courseReviews: List<CourseReviewDto> = emptyList()
)

@Serializable
data class CourseReviewDto(
    val id: Int,
    val course: Int,
    val user: Int,
    val score: Int,
    val text: String? = null,
    @SerialName("create_date") val createDate: String,
    @SerialName("vote_delta") val voteDelta: Int
)