package com.sweeteri.stepikclient

import android.app.Application
import com.sweeteri.stepikclient.presentation.common.utils.LoggerSetup

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LoggerSetup.init()
    }
}