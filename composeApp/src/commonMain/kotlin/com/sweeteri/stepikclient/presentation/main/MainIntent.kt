package com.sweeteri.stepikclient.presentation.main

sealed interface MainIntent {
    data class SearchChanged(val query: String) : MainIntent
    object LoadNextPage : MainIntent
    object Refresh : MainIntent
}