package com.wiseowl.splitride.ride.home.domain.models

import kotlin.time.Instant

data class CommuteDetail(
    val origin: String,
    val destination: String,
    val saved: Int,
    val currency: String,
    val sharedWith: String,
    val date: Instant
)
