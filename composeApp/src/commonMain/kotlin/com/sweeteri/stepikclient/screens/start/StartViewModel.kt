package com.sweeteri.stepikclient.screens.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.data.repository.AuthRepository
import com.sweeteri.stepikclient.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val onboardingShown = repository.isOnboardingShown()
                val isLoggedIn = repository.isLoggedIn()

                _startDestination.value = when {
                    !onboardingShown -> Screen.Onboarding.route
                    isLoggedIn -> Screen.Main.route
                    else -> Screen.Welcome.route
                }
            } catch (e: Exception) {
                _startDestination.value = Screen.Welcome.route
            }
        }
    }
}