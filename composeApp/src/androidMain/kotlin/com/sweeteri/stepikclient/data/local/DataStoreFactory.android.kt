package com.sweeteri.stepikclient.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.sweeteri.stepikclient.utils.AppContextHolder

fun createDataStore(): DataStore<Preferences> {
    val context = AppContextHolder.context

    return PreferenceDataStoreFactory.create {
        context.filesDir.resolve("app.preferences_pb")
    }
}