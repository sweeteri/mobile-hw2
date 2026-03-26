package com.sweeteri.stepikclient.data.repository

import com.sweeteri.stepikclient.AppPreferences
interface AuthRepository{
    suspend fun isLoggedIn(): Boolean
    suspend fun isOnboardingShown(): Boolean

    suspend fun setOnboardingShown()

}
class AuthRepositoryImpl(
    private val prefs: AppPreferences
): AuthRepository {
    override suspend fun isLoggedIn(): Boolean {
        return prefs.getToken() != null
    }
    override suspend fun isOnboardingShown(): Boolean {
        return prefs.isOnboardingShown()
    }
    override suspend fun setOnboardingShown() {
        prefs.setOnboardingShown()
    }
}