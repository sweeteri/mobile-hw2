package com.sweeteri.stepikclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sweeteri.stepikclient.utils.AndroidLogger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            App()
        }
        //AndroidLogger.logNonFatal(Exception("Test Non-Fatal Crash"))

        //AndroidLogger.logFatal(RuntimeException("Test Fatal Crash"))
    }
}
