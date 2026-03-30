package com.sweeteri.stepikclient.presentation.search

import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.core.BaseViewModel
import com.sweeteri.stepikclient.core.pagination.DefaultPaginator
import com.sweeteri.stepikclient.domain.usecase.GetCoursesUseCase
import com.sweeteri.stepikclient.presentation.common.mapper.toUiModel
import com.sweeteri.stepikclient.presentation.common.state.BaseListState
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getCoursesUseCase: GetCoursesUseCase
) : BaseViewModel<SearchUiState, SearchIntent>(SearchUiState()) {


    private val paginator = DefaultPaginator(
        initialKey = 1,

        onLoadUpdated = { isLoading ->
            updateState {
                it.copy(
                    listState = it.listState.copy(isLoading = isLoading)
                )
            }
        },

        onRequest = { page ->
            getCoursesUseCase(page, _state.value.query)
                .map { list -> list.map { it.toUiModel() } }
        },

        getNextKey = { page, _ -> page + 1 },

        onError = { error ->
            updateState {
                it.copy(
                    listState = it.listState.copy(error = error?.message)
                )
            }
        },

        onSuccess = { items, nextPage ->
            updateState { current ->
                current.copy(
                    listState = current.listState.copy(
                        items = current.listState.items + items,
                        page = nextPage,
                        error = null,
                        endReached = items.isEmpty()
                    )
                )
            }
        }
    )

    init {
        observeSearch()
    }

    override fun processIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.QueryChanged -> {
                updateState {
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
        if (state.value.listState.endReached) return

        viewModelScope.launch {
            paginator.loadNext()
        }
    }
}
