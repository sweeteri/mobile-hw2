package com.sweeteri.stepikclient.presentation.auth.login

sealed interface LoginUiEvent {
    data object LoginSuccess : LoginUiEvent
}
