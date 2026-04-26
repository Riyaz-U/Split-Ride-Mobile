package com.wiseowl.splitride.ride.common.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ScheduleType {
    @SerialName("IMMEDIATE") IMMEDIATE,
    @SerialName("FLEXIBLE") FLEXIBLE
}