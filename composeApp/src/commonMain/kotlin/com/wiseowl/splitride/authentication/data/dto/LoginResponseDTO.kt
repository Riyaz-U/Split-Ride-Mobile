package com.wiseowl.splitride.authentication.data.dto

data class LoginResponseDTO(
    val success: Boolean,
    val token: String?,
    val errorMessage: String?
)