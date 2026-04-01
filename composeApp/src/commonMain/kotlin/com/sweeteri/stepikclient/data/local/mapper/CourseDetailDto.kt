package com.sweeteri.stepikclient.data.local.mapper


import com.sweeteri.stepikclient.data.local.model.Author
import com.sweeteri.stepikclient.data.local.model.CourseDetail
import com.sweeteri.stepikclient.data.local.model.Lesson
import com.sweeteri.stepikclient.data.local.model.Section
import com.sweeteri.stepikclient.data.local.model.UnitCourse
import com.sweeteri.stepikclient.data.remote.dto.CourseDetailDto
import com.sweeteri.stepikclient.data.remote.dto.CourseReviewSummaryDto
import com.sweeteri.stepikclient.data.remote.dto.LessonDto
import com.sweeteri.stepikclient.data.remote.dto.SectionDto
import com.sweeteri.stepikclient.data.remote.dto.UnitDto


fun CourseDetailDto.toDomain(
    sections: List<SectionDto>,
    units: List<UnitDto>,
    lessons: List<LessonDto>,
    reviewSummary: CourseReviewSummaryDto? = null
): CourseDetail {

    val unitsMap = units.associateBy { it.id }
    val lessonsMap = lessons.associateBy { it.id }

    val sectionsDomain = sections.map { sectionDto ->

        val unitsDomain = sectionDto.units.mapNotNull { unitId ->
            val unitDto = unitsMap[unitId] ?: return@mapNotNull null

            val lessonsDomain = unitDto.lessonId?.let { lessonId ->
                lessonsMap[lessonId]?.let { lessonDto ->
                    listOf(
                        Lesson(
                            id = lessonDto.id.toString(),
                            title = lessonDto.title.orDefault()
                        )
                    )
                }
            } ?: emptyList()

            UnitCourse(
                id = unitDto.id.toString(),
                title = "Unit ${unitDto.id}", // title юнита нет в API
                lessons = lessonsDomain
            )
        }

        Section(
            id = sectionDto.id.toString(),
            title = sectionDto.title.orDefault(),
            units = unitsDomain
        )
    }

    return CourseDetail(
        id = id.toString(),
        title = title.orDefault(),
        description = summary.orEmpty(),
        imageUrl = cover.orEmpty(),
        authors = listOf(
            Author(
                id = "0",
                fullName = "Stepik"
            )
        ),
        average = reviewSummary?.average ?: 0.0,
        reviewCount = reviewSummary?.reviewCount ?: 0,
        lessonCount = totalUnits ?: 0,
        sections = sectionsDomain,
        learnersCount = learnersCount ?: 0
    )
}

private fun String?.orDefault() = this ?: "Без названия"