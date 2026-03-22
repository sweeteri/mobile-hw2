package com.sweeteri.stepikclient.domain.usecase
import com.sweeteri.stepikclient.data.repository.LoginRepository
class LoginUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(login: String, password: String) =
        repository.login(login, password)
}