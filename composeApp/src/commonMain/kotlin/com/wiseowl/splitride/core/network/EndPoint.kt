package com.wiseowl.splitride.core.network

enum class EndPoint(val path: String) {
    Register("/api/auth/register"),
    Login("/api/auth/login"),

    ScheduleRideIntentSearch("/api/ride-intents/schedule/search"),
    CheckRideIntentSearchStatus("/api/ride-intents/search/status")
}