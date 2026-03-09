package com.example.mobile_hw2.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.activity.compose.BackHandler
import android.app.Activity
@Composable
actual fun BackHandlerWithExit() {
    val context = LocalContext.current
    BackHandler {
        (context as? Activity)?.finish()
    }
}
