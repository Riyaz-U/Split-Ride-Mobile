package com.wiseowl.splitride.authentication.domain

interface AuthenticationService {
    suspend fun login(email: String, password: String): Result<Boolean>
    suspend fun register(firstName: String, lastName: String, email: String, password: String): Result<Boolean>
}