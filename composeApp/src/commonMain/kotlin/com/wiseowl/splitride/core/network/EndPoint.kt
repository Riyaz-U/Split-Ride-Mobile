package com.wiseowl.splitride.core.network

enum class EndPoint(val path: String) {
    Register("/api/auth/register"),
    Login("/api/auth/login"),
    CreateRideIntent("/api/ride-intents/create")
}