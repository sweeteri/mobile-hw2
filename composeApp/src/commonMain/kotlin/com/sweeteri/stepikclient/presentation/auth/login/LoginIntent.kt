package com.sweeteri.stepikclient.presentation.auth.login


sealed class LoginIntent {
    data class UsernameChanged(val value: String) : LoginIntent()
    data class PasswordChanged(val value: String) : LoginIntent()
    object TogglePasswordVisibility : LoginIntent()
    object LoginClicked : LoginIntent()
}