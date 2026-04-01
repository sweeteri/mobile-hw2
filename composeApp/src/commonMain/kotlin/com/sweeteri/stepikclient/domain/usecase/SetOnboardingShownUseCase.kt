package com.sweeteri.stepikclient.domain.usecase

import com.sweeteri.stepikclient.data.repository.AuthRepository


class SetOnboardingShownUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() {
        repository.setOnboardingShown()
    }
}