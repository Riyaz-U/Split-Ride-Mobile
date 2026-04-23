package com.wiseowl.splitride.ride.create_intent.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateRideIntentRequestDTO(
    val sourceArea: String,
    val destinationArea: String,
    val sourceLat: Double,
    val sourceLng: Double,
    val destinationLat: Double,
    val destinationLng: Double,
    val startTime: String,  // ISO format
    val flexibleMinutes: Int
)
