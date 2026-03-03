package com.example.mobile_hw2.screens.main

import androidx.compose.runtime.Immutable

@Immutable
data class MainItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String
)