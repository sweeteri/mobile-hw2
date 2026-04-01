package com.sweeteri.stepikclient.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.AppPreferences
import com.sweeteri.stepikclient.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val prefs: AppPreferences
) : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state.asStateFlow()
    private val _events = MutableSharedFlow<LoginUiEvent>()
    val events: SharedFlow<LoginUiEvent> = _events.asSharedFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.UsernameChanged -> {
                _state.update {
                    it.copy(
                        username = intent.value,
                        isLoginButtonActive =
                            intent.value.isNotBlank() && it.password.isNotBlank()
                    )
                }
            }

            is LoginIntent.PasswordChanged -> {
                _state.update {
                    it.copy(
                        password = intent.value,
                        isLoginButtonActive =
                            it.username.isNotBlank() && intent.value.isNotBlank()
                    )
                }
            }

            LoginIntent.TogglePasswordVisibility -> {
                _state.update {
                    it.copy(isPasswordVisible = !it.isPasswordVisible)
                }
            }

            LoginIntent.LoginClicked -> {
                login()
            }
        }
    }


    private fun login() {
        val current = _state.value

        viewModelScope.launch {
            val result = loginUseCase(current.username, current.password)

            result.fold(
                onSuccess = {
                    prefs.saveToken("mock_token")
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
