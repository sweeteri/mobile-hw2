package com.sweeteri.stepikclient.presentation.common.model

import androidx.compose.runtime.Immutable

@Immutable
data class CourseUiModel(
    val id: String,
    val title: String,
    val author: String,
    val imageUrl: String,
    val average: String,
    val learners: String
)