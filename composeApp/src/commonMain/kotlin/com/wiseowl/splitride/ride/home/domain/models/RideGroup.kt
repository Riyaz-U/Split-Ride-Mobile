package com.wiseowl.splitride.ride.home.domain.models

data class RideGroup(
    val id: String,
    val origin: String,
    val destination: String,
    val companion: Int,
    val occupancy: Int,
    val savingInPercent: Int,
    val startsIn: Long
)
