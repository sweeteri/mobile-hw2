package com.sweeteri.stepikclient

actual fun createAppPreferences(): AppPreferences {
    return object : AppPreferences {
        private var token: String? = null
        private var onboarding = false

        override suspend fun setOnboardingShown() {
            onboarding = true
        }

        override suspend fun isOnboardingShown(): Boolean = onboarding

        override suspend fun saveToken(token: String) {
            this.token = token
        }

        override suspend fun getToken(): String? = token

        override suspend fun clearToken() {
            token = null
        }
    }
}