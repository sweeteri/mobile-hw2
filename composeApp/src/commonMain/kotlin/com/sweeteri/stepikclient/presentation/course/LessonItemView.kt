package com.sweeteri.stepikclient.presentation.course

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sweeteri.stepikclient.data.local.model.Lesson

@Composable
fun LessonItemView(lesson: Lesson) {
    Text(
        lesson.title,
        style = MaterialTheme.typography.bodyMedium
    )
}
