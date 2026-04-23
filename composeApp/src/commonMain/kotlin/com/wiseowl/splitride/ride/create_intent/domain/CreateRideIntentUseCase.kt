package com.wiseowl.splitride.ride.create_intent.domain

import com.wiseowl.splitride.ride.common.domain.RideRepository
import kotlin.time.Instant

class CreateRideIntentUseCase(
    private val rideRepository: RideRepository
) {
    suspend operator fun invoke(
        sourceLat: Double,
        sourceLng: Double,
        destinationLat: Double,
        destinationLng: Double,
        startTime: Instant,  // ISO format
        flexibleMinutes: Int
    ){
        rideRepository.createRideIntent(
            sourceLat = sourceLat,
            sourceLng = sourceLng,
            destinationLat = destinationLat,
            destinationLng = destinationLng,
            startTime = startTime,
            flexibleMinutes = flexibleMinutes
        )
    }
}