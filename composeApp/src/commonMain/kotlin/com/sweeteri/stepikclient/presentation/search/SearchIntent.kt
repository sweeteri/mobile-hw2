package com.sweeteri.stepikclient.presentation.search

sealed interface SearchIntent {
    data class QueryChanged(val query: String) : SearchIntent
    object LoadNextPage : SearchIntent

    object Refresh: SearchIntent

}