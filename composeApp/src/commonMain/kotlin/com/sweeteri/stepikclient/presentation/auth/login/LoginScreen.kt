package com.sweeteri.stepikclient.presentation.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
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

    LoginContent(
        username = uiState.username,
        password = uiState.password,
        isPasswordVisible = uiState.isPasswordVisible,
        isLoginButtonEnabled = uiState.isLoginButtonActive,
        error = uiState.error,

        onEmailChange = {
            viewModel.onIntent(LoginIntent.UsernameChanged(it))
        },

        onPasswordChange = {
            viewModel.onIntent(LoginIntent.PasswordChanged(it))
        },

        onTogglePasswordVisibility = {
            viewModel.onIntent(LoginIntent.TogglePasswordVisibility)
        },

        onLoginClick = {
            viewModel.onIntent(LoginIntent.LoginClicked)
        },

        onRegisterClick = onRegisterClick,
        onBackClick = onBackClick
    )
}
