package com.sweeteri.stepikclient

import android.app.Application
import com.sweeteri.core.utils.LoggerSetup
import com.sweeteri.stepikclient.di.appModule
import com.sweeteri.stepikclient.utils.AndroidLogger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LoggerSetup.init()

        AndroidLogger.init()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}