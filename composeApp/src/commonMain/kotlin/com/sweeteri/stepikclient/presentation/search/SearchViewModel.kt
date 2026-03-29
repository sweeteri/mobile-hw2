package com.sweeteri.stepikclient.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.core.pagination.DefaultPaginator
import com.sweeteri.stepikclient.domain.usecase.GetCoursesUseCase
import com.sweeteri.stepikclient.presentation.common.mapper.toUiModel
import com.sweeteri.stepikclient.presentation.common.state.BaseListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()

    private val paginator = DefaultPaginator(
        initialKey = 1,

        onLoadUpdated = { isLoading ->
            _state.update {
                it.copy(
                    listState = it.listState.copy(isLoading = isLoading)
                )
            }
        },

        onRequest = { page ->
            getCoursesUseCase(page, _state.value.query)
        },

        getNextKey = { page, _ -> page + 1 },

        onError = { error ->
            _state.update {
                it.copy(
                    listState = it.listState.copy(error = error?.message)
                )
            }
        },

        onSuccess = { courses, _ ->
            _state.update { current ->
                current.copy(
                    listState = current.listState.copy(
                        items = current.listState.items + courses.map { it.toUiModel() },
                        endReached = courses.isEmpty()
                    )
                )
            }
        }
    )

    init {
        observeSearch()
    }

    fun processIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.QueryChanged -> {
                _state.update {
                    it.copy(
                        query = intent.query,
                        listState = BaseListState()
                    )
                }
                paginator.reset()
            }

            SearchIntent.LoadNextPage -> loadNext()
        }
    }

    private fun observeSearch() {
        viewModelScope.launch {
            state
                .map { it.query }
                .debounce(500)
                .distinctUntilChanged()
                .collect {
                    loadNext()
                }
        }
    }

    private fun loadNext() {
        if (_state.value.listState.endReached) return

        viewModelScope.launch {
            paginator.loadNext()
        }
    }
}


