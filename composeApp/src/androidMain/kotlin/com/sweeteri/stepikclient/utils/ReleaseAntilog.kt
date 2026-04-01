package com.sweeteri.stepikclient.utils


import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.github.aakira.napier.Antilog
import io.github.aakira.napier.LogLevel

class ReleaseAntilog : Antilog() {

    override fun performLog(
        priority: LogLevel,
        tag: String?,
        throwable: Throwable?,
        message: String?
    ) {
        if (priority == LogLevel.ERROR && throwable != null) {
            FirebaseCrashlytics.getInstance().recordException(throwable)
        }
    }
}