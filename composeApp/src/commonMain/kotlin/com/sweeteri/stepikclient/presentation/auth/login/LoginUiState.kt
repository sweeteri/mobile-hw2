package com.sweeteri.stepikclient.presentation.auth.login

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoginButtonActive: Boolean = false,
    val error: String? = null
)
