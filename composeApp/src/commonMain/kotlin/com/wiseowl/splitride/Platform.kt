package com.wiseowl.splitride

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform