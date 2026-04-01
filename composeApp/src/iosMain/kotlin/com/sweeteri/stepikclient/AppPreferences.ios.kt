package com.sweeteri.stepikclient

import com.sweeteri.stepikclient.data.local.createDataStore
actual fun createAppPreferences(): AppPreferences {
    val dataStore = createDataStore()
    return AppPreferencesImpl(dataStore)
}