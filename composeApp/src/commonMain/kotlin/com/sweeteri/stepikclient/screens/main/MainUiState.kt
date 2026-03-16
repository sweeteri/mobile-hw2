package com.sweeteri.stepikclient.screens.main

import com.sweeteri.stepikclient.data.model.Course

data class MainUiState(
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 1,
    val hasNextPage: Boolean = true
)
