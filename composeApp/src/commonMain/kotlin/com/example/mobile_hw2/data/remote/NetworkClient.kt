package com.example.mobile_hw2.data.remote

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkClient {
    private val jsonConfig = Json {
        prettyPrint = false
        isLenient = true
        ignoreUnknownKeys = true
    }
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(jsonConfig)
        }
        install(Logging) {
            level = LogLevel.INFO
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "HTTP_CLIENT", message = message)
                }
            }
        }
    }

    fun initLogger() {
        Napier.base(DebugAntilog())
    }
}
