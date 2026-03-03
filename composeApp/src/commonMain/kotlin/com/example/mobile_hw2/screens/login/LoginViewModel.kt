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
    fun onUsernameChanged(value: String) {
        _state.update {
            it.copy(
                username = value,
                isLoginButtonActive =
                    value.isNotBlank() && it.password.isNotBlank()
            )
        }
    }

    fun onPasswordChanged(value: String) {
        _state.update {
            it.copy(
                password = value,
                isLoginButtonActive =
                    it.username.isNotBlank() && value.isNotBlank()
            )
        }
    }

    fun togglePasswordVisibility() {
        _state.update {
            it.copy(isPasswordVisible = !it.isPasswordVisible)
        }
    }

    private val repository = LoginRepository()
    fun login() {
        val current = _state.value

        viewModelScope.launch {
            val result = repository.login(current.username, current.password)

            result.fold(
                onSuccess = {
                    _events.emit(LoginUiEvent.LoginSuccess)
                    _state.update { it.copy(error = null) }
                },
                onFailure = { ex ->
                    _state.update { it.copy(error = ex.message ?: "Неверный логин или пароль") }
                }
            )
        }
    }
}
