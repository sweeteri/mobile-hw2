package com.example.mobile_hw2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform