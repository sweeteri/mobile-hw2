package com.sweeteri.stepikclient.presentation.course.content

import androidx.compose.foundation.lazy.LazyListScope
import com.sweeteri.stepikclient.presentation.course.state.CourseDetailState
import com.sweeteri.stepikclient.presentation.course.components.LessonItemView
import com.sweeteri.stepikclient.presentation.course.components.SectionHeader

fun LazyListScope.curriculumItems(state: CourseDetailState) {
    val course = state.course ?: return

    course.sections.forEach { section ->

        item {
            SectionHeader(section.title)
        }

        section.units.forEach { unit ->
            item {
                LessonItemView(unit)
            }
        }
    }
}