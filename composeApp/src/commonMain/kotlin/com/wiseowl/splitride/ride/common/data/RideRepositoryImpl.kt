package com.wiseowl.splitride.ride.common.data

import com.wiseowl.splitride.core.network.ApiService
import com.wiseowl.splitride.core.network.EndPoint
import com.wiseowl.splitride.ride.common.domain.CreateRideIntentResult
import com.wiseowl.splitride.ride.common.domain.RideRepository
import com.wiseowl.splitride.ride.common.domain.model.RideIntent
import com.wiseowl.splitride.ride.create_intent.data.dto.CreateRideIntentRequestDTO
import kotlin.time.Instant

class RideRepositoryImpl(
    private val apiService: ApiService
): RideRepository {
    override fun createRideIntent(
        sourceLat: Double,
        sourceLng: Double,
        destinationLat: Double,
        destinationLng: Double,
        startTime: Instant,
        flexibleMinutes: Int,
    ): CreateRideIntentResult{
        //TODO("Yet To Implement")
        val response = apiService.get<CreateRideIntentRequestDTO>(endPoint = EndPoint.Register, body = CreateRideIntentRequestDTO())
        return CreateRideIntentResult.Success(RideIntent("", sourceLat, sourceLng, destinationLat, destinationLng, startTime, flexibleMinutes))
    }

    override fun checkRideIntentStatus(rideIntentId: String): CreateRideIntentResult {

    }
}