package com.example.mobile_hw2.screens.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoginButtonEnabled: Boolean = false,
    val error: String? = null
)