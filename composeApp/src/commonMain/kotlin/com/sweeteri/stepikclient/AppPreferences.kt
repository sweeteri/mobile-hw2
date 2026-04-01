package com.sweeteri.stepikclient


interface AppPreferences {
    suspend fun setOnboardingShown()
    suspend fun isOnboardingShown(): Boolean

    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun clearToken()
}

expect fun createAppPreferences(): AppPreferences

