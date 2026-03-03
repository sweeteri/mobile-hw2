package com.example.mobile_hw2.screens.welcome

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class WelcomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(WelcomeUiState())
    val state: StateFlow<WelcomeUiState> = _state

    fun onEmailOptInChanged(checked: Boolean) {
        _state.update {
            it.copy(isEmailOptInChecked = checked)
        }
    }
}
