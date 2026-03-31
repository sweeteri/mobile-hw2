package com.sweeteri.stepikclient.presentation.course

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sweeteri.stepikclient.data.local.model.CourseDetail

@Composable
fun CourseTabContent(
    selectedTab: Int,
    course: CourseDetail
) {
    when (selectedTab) {
        0 -> InfoTab(course)
        1 -> ReviewsTab()
        2 -> SyllabusTab(course)
    }
}

@Composable
fun InfoTab(course: CourseDetail) {
    Column(Modifier.padding(16.dp)) {
        Text(course.description)
    }
}

@Composable
fun ReviewsTab() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Отзывы пока недоступны")
    }
}

@Composable
fun SyllabusTab(course: CourseDetail) {
    LazyColumn(
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        course.sections.forEach { section ->
            item {
                Text(
                    section.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            section.units.forEach { unit ->
                unit.lessons.forEach { lesson ->
                    item {
                        LessonItemView(lesson)
                    }
                }
            }
        }
    }
}
