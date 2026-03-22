package com.sweeteri.stepikclient.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.AppPreferences
import com.sweeteri.stepikclient.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StartViewModel(private val prefs: AppPreferences) : ViewModel() {

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination

    init {
        viewModelScope.launch {
            try {
                val onboardingShown = prefs.isOnboardingShown()
                val token = prefs.getToken()

                _startDestination.value = when {
                    !onboardingShown -> {
                        Screen.Onboarding.route
                    }

                    token != null -> {
                        Screen.Main.route
                    }

                    else -> {
                        Screen.Welcome.route
                    }
                }
            } catch (e: Exception) {
                _startDestination.value = Screen.Welcome.route
            }
        }
    }
}
