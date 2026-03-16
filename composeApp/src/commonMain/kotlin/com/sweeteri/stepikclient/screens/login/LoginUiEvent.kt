package com.sweeteri.stepikclient.screens.login

sealed interface LoginUiEvent {
    data object LoginSuccess : LoginUiEvent
}
