package com.example.mobile_hw2.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_hw2.data.model.Course
import com.example.mobile_hw2.data.repository.CoursesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CoursesRepository = CoursesRepository()) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()
    private val _searchQuery = MutableStateFlow("")

    init {
        setupSearch()
    }

    @OptIn(FlowPreview::class)
    private fun setupSearch() {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    resetAndLoad(query)
                }
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
        _state.update { it.copy(searchQuery = newQuery) }
    }

    private fun resetAndLoad(query: String) {
        _state.update { it.copy(courses = emptyList(), currentPage = 1, hasNextPage = true) }
        loadNextPage(query)
    }

    fun loadNextPage(query: String = _searchQuery.value) {
        if (_state.value.isLoading || !_state.value.hasNextPage) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = repository.getCourses(_state.value.currentPage, query)

            result.onSuccess { response ->
                _state.update { currentState ->
                    val newCourses = response.courses.map { dto ->
                        Course(
                            id = dto.id.toString(),
                            title = dto.title,
                            author = dto.summary ?: "Stepik",
                            imageUrl = dto.cover ?: "",
                            rating = 0.0,
                            learnersCount = dto.learnersCount ?: 0
                        )
                    }
                    currentState.copy(
                        courses = currentState.courses + newCourses,
                        currentPage = response.meta.page + 1,
                        hasNextPage = response.meta.hasNext,
                        isLoading = false
                    )
                }
            }.onFailure { error ->
                _state.update { it.copy(isLoading = false, error = error.message) }
            }
        }
    }

    fun refresh() {
        _state.update { it.copy(courses = emptyList(), currentPage = 1, hasNextPage = true) }
        loadNextPage()
    }
}