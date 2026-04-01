package com.sweeteri.stepikclient.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sweeteri.stepikclient.domain.usecase.SetOnboardingShownUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val setOnboardingShownUseCase: SetOnboardingShownUseCase
) : ViewModel() {
    private val _events = MutableSharedFlow<Unit>()
    val events = _events.asSharedFlow()

    fun onFinish() {
        viewModelScope.launch {
            setOnboardingShownUseCase()
            _events.emit(Unit)
        }
    }
}