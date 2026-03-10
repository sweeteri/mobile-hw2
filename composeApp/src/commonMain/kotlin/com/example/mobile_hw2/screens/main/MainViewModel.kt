package com.example.mobile_hw2.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_hw2.data.model.Course
import com.example.mobile_hw2.data.repository.CoursesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
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
    private var fetchJob: Job? = null

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
        fetchJob?.cancel()

        _state.update {
            it.copy(
                courses = emptyList(),
                currentPage = 1,
                hasNextPage = true,
                error = null,
                isLoading = false
            )
        }

        loadNextPage(query)
    }

    fun loadNextPage(query: String = _searchQuery.value) {
        val currentState = _state.value
        if (currentState.isLoading || !currentState.hasNextPage) return

        fetchJob = viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            repository.getCourses(_state.value.currentPage, query)
                .onSuccess { response ->
                    _state.update { actualState ->
                        val newCourses = response.courses.map { dto ->
                            val roundedRating = if (dto.average > 0) {
                                kotlin.math.round(dto.average * 10) / 10.0
                            } else {
                                0.0
                            }
                            Course(
                                id = dto.id.toString(),
                                title = dto.title,
                                author = dto.summary ?: "Stepik",
                                imageUrl = dto.cover ?: "",
                                average = roundedRating,
                                learnersCount = dto.learnersCount ?: 0
                            )
                        }

                        actualState.copy(
                            courses = actualState.courses + newCourses,
                            currentPage = response.meta.page + 1,
                            hasNextPage = response.meta.hasNext,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }

    fun refresh() {
        resetAndLoad(_searchQuery.value)
    }
}