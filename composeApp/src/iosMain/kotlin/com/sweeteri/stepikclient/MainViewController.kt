package com.sweeteri.stepikclient

import androidx.compose.ui.window.ComposeUIViewController
import com.sweeteri.stepikclient.presentation.common.utils.LoggerSetup

fun MainViewController() = ComposeUIViewController {
    LoggerSetup.init()
    App()
}