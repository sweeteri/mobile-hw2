package com.sweeteri.stepikclient.utils


import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

object AndroidLogger {

    fun init(isDebug: Boolean) {
        if (isDebug) {
            Napier.base(DebugAntilog())
        } else {
            Napier.base(ReleaseAntilog())
        }
    }

    fun logNonFatal(throwable: Throwable, tag: String = "NonFatal") {
        Napier.e("Non-fatal error occurred", throwable = throwable, tag = tag)

        FirebaseCrashlytics.getInstance().apply {
            log(tag)
            recordException(throwable)
        }
    }
}