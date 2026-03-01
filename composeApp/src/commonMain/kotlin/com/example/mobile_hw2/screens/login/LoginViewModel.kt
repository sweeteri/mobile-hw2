package com.example.mobile_hw2.screens.login

import androidx.lifecycle.ViewModel
import LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state

    fun onEmailChange(value: String) {
        _state.update {
            it.copy(
                email = value,
                isLoginButtonEnabled =
                    value.isNotBlank() && it.password.isNotBlank()
            )
        }
    }

    fun onPasswordChange(value: String) {
        _state.update {
            it.copy(
                password = value,
                isLoginButtonEnabled =
                    it.email.isNotBlank() && value.isNotBlank()
            )
        }
    }

    fun togglePasswordVisibility() {
        _state.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    fun login() {
        val current = _state.value
        println("Logging in with ${current.email} / ${current.password}")
    }
}
