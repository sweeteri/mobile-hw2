package com.sweeteri.stepikclient.data.repository

import com.sweeteri.stepikclient.AppPreferences
import kotlin.coroutines.cancellation.CancellationException

interface LoginRepository {
    suspend fun login(username: String, password: String): Result<Boolean>
}

class LoginRepositoryImpl(private val prefs: AppPreferences) : LoginRepository {
    override suspend fun login(username: String, password: String): Result<Boolean> {
        return try {
            if (username == "test@test.com" && password == "1234") {
                prefs.saveToken("mock_token")
                Result.success(true)
            } else {
                Result.failure(Exception("Invalid credentials"))
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Result.failure(e)
        }
    }
}