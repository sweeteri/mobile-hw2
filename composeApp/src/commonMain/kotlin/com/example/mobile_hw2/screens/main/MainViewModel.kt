package com.example.mobile_hw2.screens.main

import androidx.lifecycle.ViewModel
import com.example.mobile_hw2.data.CoursesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val repository = CoursesRepository()

    private val _state = MutableStateFlow(
        MainUiState(
            courses = repository.getCourses()
        )
    )
    val state: StateFlow<MainUiState> = _state
}