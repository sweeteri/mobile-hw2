package com.example.mobile_hw2

import android.app.Application
import com.example.mobile_hw2.utils.LoggerSetup

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LoggerSetup.init()
    }
}