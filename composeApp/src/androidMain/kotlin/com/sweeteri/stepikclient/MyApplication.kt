package com.sweeteri.stepikclient

import android.app.Application
import androidx.room.Room
import com.sweeteri.stepikclient.data.local.AppDatabase
import com.sweeteri.stepikclient.data.remote.StepikApiClient
import com.sweeteri.stepikclient.utils.AppContextHolder
import com.sweeteri.stepikclient.utils.LoggerSetup

class MyApplication : Application() {
    lateinit var db: AppDatabase
    lateinit var prefs: AppPreferences
    lateinit var apiClient: StepikApiClient
    override fun onCreate() {
        super.onCreate()
        LoggerSetup.init()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_db"
        ).build()
        AppContextHolder.context = applicationContext
        prefs = createAppPreferences()
        apiClient = StepikApiClient()
    }
}