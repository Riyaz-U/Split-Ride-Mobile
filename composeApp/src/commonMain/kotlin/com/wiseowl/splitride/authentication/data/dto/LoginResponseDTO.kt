package com.wiseowl.splitride.authentication.data.dto

data class LoginResponseDTO(
    val accessToken: String,
    val accessTokenExpirationSec: Long,
    val refreshToken: String
)