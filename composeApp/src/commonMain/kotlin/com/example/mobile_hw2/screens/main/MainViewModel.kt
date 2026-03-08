package com.example.mobile_hw2.screens.main

import androidx.lifecycle.ViewModel
import com.example.mobile_hw2.data.repository.CoursesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val repository: CoursesRepository = CoursesRepository()
) : ViewModel() {
    private val _state = MutableStateFlow(
        MainUiState(
            courses = repository.getCourses()
        )
    )
    val state: StateFlow<MainUiState> = _state.asStateFlow()
}