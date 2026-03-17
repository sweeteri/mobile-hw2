package com.sweeteri.stepikclient

import com.sweeteri.stepikclient.data.local.AppDataStore
import com.sweeteri.stepikclient.data.local.AppPreferencesImpl

actual fun createAppPreferences(): AppPreferences {
    return AppPreferencesImpl(AppDataStore.dataStore)
}