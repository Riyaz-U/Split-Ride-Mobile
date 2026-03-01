package com.wiseowl.splitride.authentication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
    val accessToken: String,
    val accessTokenExpirationSec: Long,
    val refreshToken: String
)