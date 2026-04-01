package com.sweeteri.stepikclient.presentation.course

import com.sweeteri.stepikclient.data.local.model.CourseDetail

sealed interface CourseDetailUiState {
    object Loading : CourseDetailUiState
    data class Success(val course: CourseDetail) : CourseDetailUiState
    data class Error(val throwable: Throwable) : CourseDetailUiState
}