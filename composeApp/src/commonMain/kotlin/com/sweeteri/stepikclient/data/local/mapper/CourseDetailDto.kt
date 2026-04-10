package com.sweeteri.stepikclient.data.local.mapper


import com.sweeteri.stepikclient.data.local.model.Author
import com.sweeteri.stepikclient.data.local.model.CourseDetail
import com.sweeteri.stepikclient.data.local.model.CoursePrice
import com.sweeteri.stepikclient.data.local.model.CourseReview
import com.sweeteri.stepikclient.data.local.model.Lesson
import com.sweeteri.stepikclient.data.local.model.Section
import com.sweeteri.stepikclient.data.local.model.UnitCourse
import com.sweeteri.stepikclient.data.remote.dto.CourseDetailDto
import com.sweeteri.stepikclient.data.remote.dto.CourseReviewDto
import com.sweeteri.stepikclient.data.remote.dto.CourseReviewSummaryDto
import com.sweeteri.stepikclient.data.remote.dto.LessonDto
import com.sweeteri.stepikclient.data.remote.dto.SectionDto
import com.sweeteri.stepikclient.data.remote.dto.UnitDto
import com.sweeteri.stepikclient.data.remote.dto.UserDto
import kotlin.collections.emptyMap
import kotlin.math.round

fun formatLearnersCount(count: Int): String {
    return when {
        count >= 1_000_000 -> String.format("%.1fM", count / 1_000_000.0)
        count >= 1_000 -> String.format("%.1fk", count / 1_000.0)
        else -> count.toString()
    }
}

fun CourseDetailDto.toDomain(
    sections: List<SectionDto>,
    units: List<UnitDto>,
    lessons: List<LessonDto>,
    reviewSummary: CourseReviewSummaryDto? = null,
    users: List<UserDto> = emptyList()
): CourseDetail {

    val unitsMap = units.associateBy { it.id }
    val lessonsMap = lessons.associateBy { it.id }
    val authorsDomain = users.map {
        Author(
            id = it.id.toString(),
            fullName = it.fullName,
        )
    }
    val distributionMap = reviewSummary?.distribution?.toDistributionMap() ?: emptyMap()

    val sectionsDomain = sections.mapIndexed { sectionIndex, sectionDto ->

        val unitsDomain = sectionDto.units.mapIndexedNotNull { unitIndex, unitId ->

            val unitDto = unitsMap[unitId] ?: return@mapIndexedNotNull null
            val lessonDto = unitDto.lessonId?.let { lessonsMap[it] }
                ?: return@mapIndexedNotNull null

            val lesson = Lesson(
                id = lessonDto.id.toString(),
                title = lessonDto.title.orDefault(),
                coverUrl = lessonDto.coverUrl.orEmpty(),
                timeToComplete = lessonDto.timeToComplete,
                passedBy = lessonDto.passedBy,
                voteDelta = lessonDto.voteDelta
            )

            UnitCourse(
                id = unitDto.id.toString(),
                index = "${sectionIndex + 1}.${unitIndex + 1}",
                lesson = lesson
            )
        }

        Section(
            id = sectionDto.id.toString(),
            title = "${sectionIndex + 1} ${sectionDto.title.orDefault()}",
            units = unitsDomain
        )
    }

    return CourseDetail(
        id = id.toString(),
        title = title.orDefault(),
        description = description.orEmpty(),
        summary = summary.orEmpty(),
        cover = this.cover ?: "",
        requirements = requirements ?: "",
        targetAudience = targetAudience ?: "",
        acquiredSkills = this.acquiredSkills ?: emptyList(),
        imageUrl = cover.orEmpty(),
        totalUnits = totalUnits ?: 0,
        quizzesCount = quizzesCount ?: 0,
        price = displayPrice.orEmpty(),
        isPaid = isPaid ?: false,
        authors = authorsDomain,
        average = round((reviewSummary?.average ?: 0.0) * 10)/ 10.0,
        reviewCount = reviewSummary?.reviewCount ?: 0,
        distribution = distributionMap,
        lessonCount = lessons.size,
        sections = sectionsDomain,
        learnersCount = formatLearnersCount(learnersCount ?: 0),
        reviewSummary = reviewSummary,
        timeToComplete = timeToComplete,
        isCertificateIssued = isCertificateIssued ?: false,
        certificateRegularThreshold = certificateRegularThreshold,
        certificateDistinctionThreshold = certificateDistinctionThreshold,
        difficulty = difficulty.orEmpty(),
        learningFormat = learningFormat.orEmpty(),
        language = language.orEmpty(),
        pricing = CoursePrice(
            isPaid = isPaid ?: false,
            displayPrice = displayPrice.orEmpty(),
            price = price,
            currencyCode = currencyCode
        )
    )
}

fun CourseReviewDto.toDomain(
    userName: String,
    userAvatar: String?
) = CourseReview(
    id = id.toString(),
    courseId = course.toString(),
    userId = user.toString(),
    score = score,
    text = text.orEmpty(),
    date = createDate,
    votes = voteDelta,
    userName = userName,
    userAvatar = userAvatar ?: ""
)

private fun String?.orDefault() = this ?: "Без названия"