package com.wiseowl.splitride.authentication.domain

interface AuthenticationService {
    suspend fun login(email: String, password: String): LoginResult
    suspend fun register(firstName: String, lastName: String, email: String, password: String): RegistrationResult
}

sealed class LoginResult{
    object NetworkError: LoginResult()
    object AuthenticationError: LoginResult()
    object Success: LoginResult()
}

sealed class RegistrationResult{
    object NetworkError: RegistrationResult()
    object RegistrationError: RegistrationResult()
    object Success: RegistrationResult()
}