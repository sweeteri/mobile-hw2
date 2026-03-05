package com.example.mobile_hw2.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


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
    val onEmailChange = remember { { value: String -> viewModel.onUsernameChanged(value) } }
    val onPasswordChange = remember { { value: String -> viewModel.onPasswordChanged(value) } }
    val onTogglePasswordVisibility = remember { { viewModel.togglePasswordVisibility() } }
    val onLoginClick = remember { { viewModel.login() } }

    LoginContent(
        username = uiState.username,
        password = uiState.password,
        isPasswordVisible = uiState.isPasswordVisible,
        isLoginButtonEnabled = uiState.isLoginButtonActive,
        error = uiState.error,
        onEmailChange = onEmailChange,
        onPasswordChange = onPasswordChange,
        onTogglePasswordVisibility = onTogglePasswordVisibility,
        onLoginClick = onLoginClick,
        onRegisterClick = onRegisterClick,
        onBackClick = onBackClick
    )
}
