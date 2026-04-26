package com.wiseowl.splitride.ride.common.data

import com.wiseowl.splitride.core.network.ApiService
import com.wiseowl.splitride.core.network.EndPoint
import com.wiseowl.splitride.ride.common.domain.CreateRideIntentResult
import com.wiseowl.splitride.ride.common.domain.RideIntentStatusResult
import com.wiseowl.splitride.ride.common.domain.RideRepository
import com.wiseowl.splitride.ride.common.domain.model.RideIntent
import com.wiseowl.splitride.ride.common.domain.model.ScheduleType
import com.wiseowl.splitride.ride.common.domain.model.Status
import com.wiseowl.splitride.ride.create_intent.data.dto.CreateRideIntentRequestDTO
import kotlin.time.Instant

class RideRepositoryImpl(
    private val apiService: ApiService
): RideRepository {
    override suspend fun scheduleRideIntentSearch(
        sourceLat: Double,
        sourceLng: Double,
        destinationLat: Double,
        destinationLng: Double,
        scheduleType: ScheduleType,
        startTime: Instant?,
        flexibleMinutes: Int,
    ): CreateRideIntentResult{
        val response = apiService.post<RideIntent, CreateRideIntentRequestDTO>(
            endPoint = EndPoint.ScheduleRideIntentSearch,
            body = CreateRideIntentRequestDTO(
                sourceLat = sourceLat,
                sourceLng = sourceLng,
                destinationLat = destinationLat,
                destinationLng = destinationLng,
                scheduleType = scheduleType,
                startTime = startTime,
                flexibleMinutes = flexibleMinutes,
            )
        )

        if(response.success){
            return CreateRideIntentResult.Success(
                response.data!!
            )
        } else{
            TODO("IDENTIFY ERROR AND PARSE")
        }
    }

    override suspend fun checkRideIntentStatus(rideIntentId: Long): RideIntentStatusResult {
        val response = apiService.get<String>(
            endPoint = EndPoint.CheckRideIntentSearchStatus
        )

        if(response.success){
            return when{
                response.data!!.startsWith("Idle") -> RideIntentStatusResult.Success(Status.Idle)
                response.data.startsWith("Searching") -> RideIntentStatusResult.Success(Status.Searching)
                response.data.startsWith("Grouped") -> RideIntentStatusResult.Success(Status.Grouped(response.data.substringAfter("|").toLong()))
                else -> RideIntentStatusResult.UnknownError
            }
        } else{
            TODO("IDENTIFY ERROR AND PARSE")
        }
    }
}