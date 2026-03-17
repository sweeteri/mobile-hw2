package com.sweeteri.stepikclient.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sweeteri.stepikclient.AppPreferences
import kotlinx.coroutines.flow.first

class AppPreferencesImpl(
    private val dataStore: DataStore<Preferences>
) : AppPreferences {

    companion object {
        val ONBOARDING = booleanPreferencesKey("onboarding")
        val TOKEN = stringPreferencesKey("token")
    }

    override suspend fun setOnboardingShown() {
        dataStore.edit { it[ONBOARDING] = true }
    }

    override suspend fun isOnboardingShown(): Boolean {
        return dataStore.data.first()[ONBOARDING] ?: false
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { it[TOKEN] = token }
    }

    override suspend fun getToken(): String? {
        return dataStore.data.first()[TOKEN]
    }

    override suspend fun clearToken() {
        dataStore.edit { it.remove(TOKEN) }
    }
}
