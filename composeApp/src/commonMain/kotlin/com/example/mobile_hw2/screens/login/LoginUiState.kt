package com.example.mobile_hw2.screens.login

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoginButtonActive: Boolean = false,
    val error: String? = null
)