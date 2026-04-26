package com.wiseowl.splitride.ride.create_intent.data.dto

import com.wiseowl.splitride.ride.common.domain.model.ScheduleType
import kotlin.time.Instant
import kotlinx.serialization.Serializable

@Serializable
data class CreateRideIntentRequestDTO(
    val sourceLat: Double,
    val sourceLng: Double,
    val destinationLat: Double,
    val destinationLng: Double,
    val scheduleType: ScheduleType,
    val startTime: Instant? = null,
    val flexibleMinutes: Int
)
