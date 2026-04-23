package com.wiseowl.splitride.ride.common.domain.model

import kotlin.time.Instant

data class RideIntent(
    val id: String,
    val sourceLat: Double,
    val sourceLng: Double,
    val destinationLat: Double,
    val destinationLng: Double,
    val startTime: Instant,  // ISO format
    val flexibleMinutes: Int
)
