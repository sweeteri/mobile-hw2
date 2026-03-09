package com.example.mobile_hw2.screens.main

import com.example.mobile_hw2.data.model.Course

data class MainUiState(
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 1,
    val hasNextPage: Boolean = true
)