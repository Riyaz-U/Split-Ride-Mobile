package com.wiseowl.splitride.ride.common.domain

import com.wiseowl.splitride.ride.common.domain.model.RideIntent
import com.wiseowl.splitride.ride.common.domain.model.ScheduleType
import com.wiseowl.splitride.ride.common.domain.model.Status
import kotlin.time.Instant

interface RideRepository {
    suspend fun scheduleRideIntentSearch(
        sourceLat: Double,
        sourceLng: Double,
        destinationLat: Double,
        destinationLng: Double,
        scheduleType: ScheduleType,
        startTime: Instant? = null,
        flexibleMinutes: Int
    ): CreateRideIntentResult

    suspend fun checkRideIntentStatus(
        rideIntentId: Long
    ): RideIntentStatusResult
}

sealed class CreateRideIntentResult{
    data class Success(val rideIntent: RideIntent): CreateRideIntentResult()
    data class InvalidInput(val message: String): CreateRideIntentResult()
    object NetworkError: CreateRideIntentResult()
    object AuthenticationError: CreateRideIntentResult()
    object UnknownError: CreateRideIntentResult()
}

sealed class RideIntentStatusResult{
    data class Success(val status: Status): RideIntentStatusResult()
    object NetworkError: RideIntentStatusResult()
    object AuthenticationError: RideIntentStatusResult()
    object UnknownError: RideIntentStatusResult()
}