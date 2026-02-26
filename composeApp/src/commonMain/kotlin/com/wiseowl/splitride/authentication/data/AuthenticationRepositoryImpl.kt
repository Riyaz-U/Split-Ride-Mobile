package com.wiseowl.splitride.authentication.data

import com.wiseowl.splitride.authentication.data.dto.LoginResponseDTO
import com.wiseowl.splitride.authentication.domain.AuthenticationService
import com.wiseowl.splitride.core.network.ApiService
import com.wiseowl.splitride.core.network.EndPoint

class AuthenticationRepositoryImpl(
    val apiService: ApiService,
) : AuthenticationService {

    override suspend fun login(
        email: String,
        password: String,
    ): Result<Boolean> {
        return try {
            val response = apiService.post<LoginResponseDTO, Map<String, String>>(
                endPoint = EndPoint.Login,
                body = mapOf(
                    "email" to email,
                    "password" to password
                )
            )
            if (response.success) {
                Result.success(true)
            } else {
                Result.failure(Exception(response.errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): Result<Boolean> {
        return try {
            val response = apiService.post<Unit, Map<String, String>>(
                endPoint = EndPoint.Register,
                body = mapOf(
                    "firstName" to firstName,
                    "lastName" to lastName,
                    "email" to email,
                    "password" to password
                )
            )
            if (response.success) Result.success(true)
            else Result.failure(Exception(response.errorMessage))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}