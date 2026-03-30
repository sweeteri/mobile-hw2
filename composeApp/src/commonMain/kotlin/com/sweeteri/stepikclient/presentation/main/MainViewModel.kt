package com.sweeteri.stepikclient.presentation.main

import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.core.BaseViewModel
import com.sweeteri.stepikclient.core.pagination.DefaultPaginator
import com.sweeteri.stepikclient.domain.usecase.GetCoursesUseCase
import com.sweeteri.stepikclient.presentation.common.mapper.toUiModel
import com.sweeteri.stepikclient.presentation.common.state.BaseListState
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCoursesUseCase: GetCoursesUseCase
) : BaseViewModel<MainUiState, MainIntent>(MainUiState()) {


    private val paginator = DefaultPaginator(
        initialKey = 1,
        onLoadUpdated = { isLoading ->
            updateState { it.copy(listState = it.listState.copy(isLoading = isLoading)) }
        },
        onRequest = { nextKey ->
            getCoursesUseCase(nextKey, "")
                .map { it.map { course -> course.toUiModel() } }
        },
        getNextKey = { page, _ -> page + 1 },
        onError = { error ->
            updateState { it.copy(listState = it.listState.copy(error = error?.message)) }
        },
        onSuccess = { items, nextPage ->
            updateState {
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

    override fun processIntent(intent: MainIntent) {
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
        updateState {
            it.copy(
                listState = BaseListState()
            )
        }
        loadNext()
    }
}
