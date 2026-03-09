package com.example.mobile_hw2.screens.login

sealed interface LoginUiEvent {
    data object LoginSuccess : LoginUiEvent
}
