package com.wiseowl.splitride.core.network.response

import kotlinx.serialization.Serializable

@Serializable
data class SplitRideResponse<T>(
    val success: Boolean,
    val status: Int,
    val data: T?,
    val errorMessage: String?
)