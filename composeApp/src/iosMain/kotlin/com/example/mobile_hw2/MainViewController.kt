package com.example.mobile_hw2

import androidx.compose.ui.window.ComposeUIViewController
import com.example.mobile_hw2.utils.LoggerSetup

fun MainViewController() = ComposeUIViewController {
    LoggerSetup.init()
    App()
}