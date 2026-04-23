package com.wiseowl.splitride.ride.common.domain

import com.wiseowl.splitride.ride.common.domain.model.RideIntent
import kotlin.time.Instant

interface RideRepository {
    fun createRideIntent(
        sourceLat: Double,
        sourceLng: Double,
        destinationLat: Double,
        destinationLng: Double,
        startTime: Instant,  // ISO format
        flexibleMinutes: Int
    ): CreateRideIntentResult

    fun checkRideIntentStatus(
        rideIntentId: String
    ): CreateRideIntentResult
}

sealed class CreateRideIntentResult{
    data class Success(val rideIntent: RideIntent): CreateRideIntentResult()
    data class InvalidInput(val message: String): CreateRideIntentResult()
    object NetworkError: CreateRideIntentResult()
    object AuthenticationError: CreateRideIntentResult()
    object UnknownError: CreateRideIntentResult()
}