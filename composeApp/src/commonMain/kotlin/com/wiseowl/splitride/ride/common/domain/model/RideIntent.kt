package com.wiseowl.splitride.ride.common.domain.model

import kotlin.time.Instant

data class RideIntent(
    val id: String,
    val sourceLat: Double,
    val sourceLng: Double,
    val destinationLat: Double,
    val destinationLng: Double,
    val startTime: Instant,  // ISO format
    val flexibleMinutes: Int,
    val status: Status
)

sealed class Status{
    object Idle: Status()
    object Searching: Status()
    data class Grouped(val groupId: Long): Status()
    object Ongoing: Status()
    object Completed: Status()
    object Cancelled: Status()
}