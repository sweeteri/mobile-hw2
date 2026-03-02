package com.example.mobile_hw2.screens.login

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onRegisterClick: () -> Unit = {},
    onBackClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                LoginUiEvent.LoginSuccess -> onLoginSuccess()
            }
        }
    }
    val onEmailChange = remember { { value: String -> viewModel.onEmailChange(value) } }
    val onPasswordChange = remember { { value: String -> viewModel.onPasswordChange(value) } }
    val onTogglePasswordVisibility = remember { { viewModel.togglePasswordVisibility() } }
    val onLoginClick = remember { { viewModel.login() } }

    LoginContent(
        email = uiState.email,
        password = uiState.password,
        isPasswordVisible = uiState.isPasswordVisible,
        isLoginButtonEnabled = uiState.isLoginButtonEnabled,
        onEmailChange = onEmailChange,
        onPasswordChange = onPasswordChange,
        onTogglePasswordVisibility = onTogglePasswordVisibility,
        onLoginClick = onLoginClick,
        onRegisterClick = onRegisterClick,
        onBackClick = onBackClick
    )
}
