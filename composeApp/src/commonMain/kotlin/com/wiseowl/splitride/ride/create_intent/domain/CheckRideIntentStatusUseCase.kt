package com.wiseowl.splitride.ride.create_intent.domain

import com.wiseowl.splitride.ride.common.domain.RideIntentStatusResult
import com.wiseowl.splitride.ride.common.domain.RideRepository
import com.wiseowl.splitride.ride.common.domain.model.Status

class CheckRideIntentStatusUseCase(
    private val rideRepository: RideRepository
) {
    suspend operator fun invoke(
        rideIntentId: Long
    ): Result<Status>{
        return when(val response = rideRepository.checkRideIntentStatus(rideIntentId)){
            is RideIntentStatusResult.Success ->  Result.success(response.status)
            RideIntentStatusResult.AuthenticationError -> TODO()
            RideIntentStatusResult.NetworkError -> TODO()
            RideIntentStatusResult.UnknownError -> TODO()
        }
    }
}