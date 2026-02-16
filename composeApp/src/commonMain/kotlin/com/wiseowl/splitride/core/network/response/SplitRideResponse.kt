package com.wiseowl.splitride.core.network.response

data class SplitRideResponse<T>(
    val success: Boolean,
    val status: Int,
    val data: T?,
    val errorMessage: String?
)