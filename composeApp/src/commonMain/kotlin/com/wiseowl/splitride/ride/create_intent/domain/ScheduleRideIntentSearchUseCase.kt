package com.wiseowl.splitride.ride.create_intent.domain

import com.wiseowl.splitride.ride.common.domain.CreateRideIntentResult
import com.wiseowl.splitride.ride.common.domain.RideRepository
import com.wiseowl.splitride.ride.common.domain.model.RideIntent
import com.wiseowl.splitride.ride.common.domain.model.ScheduleType
import kotlin.time.Instant

class ScheduleRideIntentSearchUseCase(
    private val rideRepository: RideRepository
) {
    suspend operator fun invoke(
        sourceLat: Double,
        sourceLng: Double,
        destinationLat: Double,
        destinationLng: Double,
        scheduleType: ScheduleType,
        startTime: Instant? = null,
        flexibleMinutes: Int
    ): Result<RideIntent> {
        val response = rideRepository.scheduleRideIntentSearch(
            sourceLat = sourceLat,
            sourceLng = sourceLng,
            destinationLat = destinationLat,
            destinationLng = destinationLng,
            scheduleType = scheduleType,
            startTime = startTime,
            flexibleMinutes = flexibleMinutes
        )

        return when (response) {
            is CreateRideIntentResult.Success -> Result.success(response.rideIntent)
            is CreateRideIntentResult.InvalidInput -> TODO()
            CreateRideIntentResult.AuthenticationError -> TODO()
            CreateRideIntentResult.NetworkError -> TODO()
            CreateRideIntentResult.UnknownError -> TODO()
        }
    }
}
