package com.sweeteri.stepikclient

import com.sweeteri.stepikclient.data.local.AppPreferencesImpl
import com.sweeteri.stepikclient.data.local.createDataStore
import com.sweeteri.stepikclient.utils.AppContextHolder

actual fun createAppPreferences(): AppPreferences {
    val dataStore = createDataStore(AppContextHolder.context)
    return AppPreferencesImpl(dataStore)
}