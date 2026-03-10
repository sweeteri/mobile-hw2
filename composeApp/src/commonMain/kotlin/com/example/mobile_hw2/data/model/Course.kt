package com.example.mobile_hw2.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class Course(
    val id: String,
    val title: String,
    val author: String,
    val imageUrl: String,
    val average: Double = 0.0,
    val learnersCount: Int = 0
)
