package com.sweeteri.stepikclient.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.core.pagination.DefaultPaginator
import com.sweeteri.stepikclient.domain.usecase.GetCoursesUseCase
import com.sweeteri.stepikclient.presentation.common.mapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()

    private val paginator = DefaultPaginator(
        initialKey = 1,
        onLoadUpdated = { isLoading ->
            _state.update { it.copy(listState = it.listState.copy(isLoading = isLoading)) }
        },
        onRequest = { nextKey ->
            getCoursesUseCase(nextKey, "")
                .map { it.map { course -> course.toUiModel() } }
        },
        getNextKey = { page, _ -> page + 1 },
        onError = { error ->
            _state.update { it.copy(listState = it.listState.copy(error = error?.message)) }
        },
        onSuccess = { items, nextPage ->
            _state.update {
                val updatedItems = it.listState.items + items
                it.copy(
                    listState = it.listState.copy(
                        items = updatedItems,
                        page = nextPage,
                        error = null
                    )
                )
            }
        }
    )

    init {
        loadNext()
    }

    fun processIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.LoadNextPage -> loadNext()
            MainIntent.Refresh -> refresh()
        }
    }

    private fun loadNext() {
        viewModelScope.launch {
            paginator.loadNext()
        }
    }

    private fun refresh() {
        paginator.reset()
        _state.update {  it.copy(listState = it.listState.copy(items = emptyList(), page = 1, error = null)) }
        loadNext()
    }
}
