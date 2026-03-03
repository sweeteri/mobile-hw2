package com.example.mobile_hw2.screens.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    private val _state = MutableStateFlow(
        MainUiState(
            items = repository.getList()
        )
    )
    val state: StateFlow<MainUiState> = _state
}