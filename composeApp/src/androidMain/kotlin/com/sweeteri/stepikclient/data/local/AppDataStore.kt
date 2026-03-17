package com.sweeteri.stepikclient.data.local


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.sweeteri.stepikclient.utils.AppContextHolder

object AppDataStore {
    val dataStore: DataStore<Preferences> by lazy {
        PreferenceDataStoreFactory.create {
            AppContextHolder.context.filesDir.resolve("app.preferences_pb")
        }
    }
}