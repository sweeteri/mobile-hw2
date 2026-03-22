package com.sweeteri.stepikclient.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val getCoursesUseCase: GetCoursesUseCase) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()
    private var fetchJob: Job? = null

    init {
        setupSearch()
    }

    fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.SearchChanged -> {
                _state.update { it.copy(searchQuery = intent.query) }
            }

            MainIntent.LoadNextPage -> {
                loadNextPageInternal()
            }

            MainIntent.Refresh -> {
                resetAndLoad(_state.value.searchQuery)
            }
        }
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

    private fun resetAndLoad(query: String) {
        fetchJob?.cancel()

        _state.update {
            it.copy(
                courses = emptyList(),
                currentPage = 1,
                hasNextPage = true,
                error = null,
                isLoading = false,
                isRefreshing = true
            )
        }

        loadNextPageInternal(query, isRefresh = true)
    }

    fun loadNextPageInternal(
        query: String = _state.value.searchQuery,
        isRefresh: Boolean = false
    ) {
        val currentState = _state.value
        if (currentState.isLoading || !currentState.hasNextPage || fetchJob?.isActive == true) return

        fetchJob = viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            getCoursesUseCase(currentState.currentPage, query)
                .onSuccess { courses ->
                    _state.update { actual ->
                        actual.copy(
                            courses = actual.courses + courses,
                            currentPage = actual.currentPage + 1,
                            hasNextPage = courses.isNotEmpty(),
                            isLoading = false,
                            isRefreshing = if (isRefresh) false else actual.isRefreshing
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            error = error.message
                        )
                    }
                }
        }
    }
}
