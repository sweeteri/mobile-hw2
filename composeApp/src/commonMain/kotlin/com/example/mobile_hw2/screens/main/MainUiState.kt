package com.example.mobile_hw2.screens.main

import com.example.mobile_hw2.data.model.Course

data class MainUiState(
    val courses: List<Course> = emptyList(),
)