package com.kingslayer06.clockapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform