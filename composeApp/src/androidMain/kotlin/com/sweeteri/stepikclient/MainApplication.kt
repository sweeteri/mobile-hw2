package com.sweeteri.stepikclient

import android.app.Application
import com.sweeteri.stepikclient.di.appModule
import com.sweeteri.stepikclient.core.utils.LoggerSetup
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LoggerSetup.init()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}