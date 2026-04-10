package com.sweeteri.stepikclient.data.local.model

import androidx.compose.runtime.Immutable
import com.sweeteri.stepikclient.data.remote.dto.CourseReviewSummaryDto


@Immutable
data class CourseDetail(
    val id: String,
    val title: String,
    val description: String = "",
    val summary: String = "",
    val cover: String = "",
    val requirements: String = "",
    val targetAudience: String = "",
    val acquiredSkills: List<String> = emptyList(),
    val imageUrl: String = "",
    val totalUnits: Int = 0,
    val quizzesCount: Int = 0,
    val price: String = "",
    val isPaid: Boolean = false,
    val authors: List<Author> = emptyList(),
    val average: Double = 0.0,
    val reviewCount: Int = 0,
    val distribution: Map<Int, Int> = emptyMap(),
    val lessonCount: Int = 0,
    val learnersCount: String = "",
    val sections: List<Section> = emptyList(),
    val isEnrolled: Boolean = false,
    val reviews: List<CourseReview> = emptyList(),
    val reviewSummary: CourseReviewSummaryDto? = null,
    val timeToComplete:Int=0,
    val isCertificateIssued: Boolean = false,
    val certificateRegularThreshold: Int? = null,
    val certificateDistinctionThreshold: Int? = null,

    val difficulty: String = "",
    val learningFormat: String = "",
    val language: String = "",
    val pricing: CoursePrice
)
@Immutable
data class CoursePrice(
    val isPaid: Boolean,
    val displayPrice: String,
    val price: Double?,
    val currencyCode: String?
)
@Immutable
data class Author(
    val id: String,
    val fullName: String
)

@Immutable
data class Section(
    val id: String,
    val title: String,
    val units: List<UnitCourse> = emptyList()
)

@Immutable
data class UnitCourse(
    val id: String,
    val index: String,
    val lesson: Lesson
)

@Immutable
data class Lesson(
    val id: String,
    val title: String,
    val coverUrl: String = "",
    val timeToComplete: Int = 0,
    val passedBy: Int = 0,
    val voteDelta: Int = 0
)
@Immutable
data class CourseReview(
    val id: String,
    val courseId: String,
    val userId: String,
    val score: Int,
    val text: String,
    val date: String,
    val votes: Int,
    val userName: String,
    val userAvatar: String?
)
data class ReviewPage(
    val reviews: List<CourseReview>,
    val total: Int,
    val average: Double,
    val distribution: Map<Int, Int>
)
data class ReviewStats(
    val average: Float,
    val total: Int,
    val distribution: Map<Int, Int>
)
