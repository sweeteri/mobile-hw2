package com.sweeteri.stepikclient.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.data.repository.CoursesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CoursesRepository) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()
    private var fetchJob: Job? = null

    init {
        setupSearch()
    }

    @OptIn(FlowPreview::class)
    private fun setupSearch() {
        viewModelScope.launch {
            state
                .map { it.searchQuery }
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    resetAndLoad(query)
                }
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        _state.update {
            it.copy(searchQuery = newQuery)
        }
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

    fun loadNextPage(query: String = _state.value.searchQuery) {
        val currentState = _state.value
        if (currentState.isLoading || !currentState.hasNextPage || fetchJob?.isActive == true) return

        fetchJob = viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            repository.getCourses(currentState.currentPage, query)
                .onSuccess { courses ->
                    _state.update { actual ->
                        actual.copy(
                            courses = actual.courses + courses,
                            currentPage = actual.currentPage + 1,
                            hasNextPage = courses.isNotEmpty(),
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
        resetAndLoad(_state.value.searchQuery)
    }
}
