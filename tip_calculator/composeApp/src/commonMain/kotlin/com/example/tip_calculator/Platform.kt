package com.example.tip_calculator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform