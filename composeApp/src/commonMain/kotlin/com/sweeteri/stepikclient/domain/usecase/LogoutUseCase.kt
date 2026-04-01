package com.sweeteri.stepikclient.domain.usecase
import com.sweeteri.stepikclient.data.repository.ProfileRepository
class LogoutUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() {
        repository.logout()
    }
}