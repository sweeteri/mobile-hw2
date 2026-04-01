package com.sweeteri.stepikclient.presentation.course

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sweeteri.stepikclient.data.local.model.CourseDetail

@Composable
fun CourseContent(
    course: CourseDetail,
    padding: PaddingValues,
    onEnroll: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        CourseHeader(course, onEnroll)

        CourseTabs(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )

        CourseTabContent(
            selectedTab = selectedTab,
            course = course
        )
    }
}
