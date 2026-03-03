package com.example.mobile_hw2.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state
    private val _events = MutableSharedFlow<LoginUiEvent>()
    val events: SharedFlow<LoginUiEvent> = _events
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

        if (current.email == "test@test.com" && current.password == "1234") {
            viewModelScope.launch {
                _events.emit(LoginUiEvent.LoginSuccess)
            }
        } else {
            _state.update {
                it.copy(error = "Неверный логин или пароль")
            }
        }
    }
}
