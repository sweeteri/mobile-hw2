package com.sweeteri.stepikclient.presentation.common.state

data class BaseListState<T>(
    val items: List<T> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)
