package com.sweeteri.stepikclient

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
