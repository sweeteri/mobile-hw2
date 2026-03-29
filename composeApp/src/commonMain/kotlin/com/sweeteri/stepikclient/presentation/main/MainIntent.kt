package com.sweeteri.stepikclient.presentation.main

sealed interface MainIntent {
    object LoadNextPage : MainIntent
    object Refresh : MainIntent
}
