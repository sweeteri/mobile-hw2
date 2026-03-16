package com.example.mobile_hw2.utils

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

object LoggerSetup {
    fun init() {
        Napier.base(DebugAntilog())
    }
}