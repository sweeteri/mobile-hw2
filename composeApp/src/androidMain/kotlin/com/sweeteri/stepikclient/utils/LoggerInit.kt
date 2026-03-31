package com.sweeteri.stepikclient.utils


import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

object AndroidLogger {

    fun init() {
        Napier.base(DebugAntilog())
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            logFatal(throwable, "UncaughtException in ${thread.name}")
        }
    }

    fun logNonFatal(throwable: Throwable, tag: String = "NonFatal") {
        Napier.e("Non-fatal error occurred", throwable = throwable, tag = tag)
    }

    fun logFatal(throwable: Throwable, tag: String = "Fatal") {
        Napier.e("Fatal error occurred", throwable = throwable, tag = tag)
        throw throwable
    }
}