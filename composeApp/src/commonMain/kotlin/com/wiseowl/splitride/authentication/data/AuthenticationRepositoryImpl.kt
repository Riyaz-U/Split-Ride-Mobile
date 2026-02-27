package com.wiseowl.splitride.authentication.data

import com.wiseowl.splitride.authentication.data.dto.LoginResponseDTO
import com.wiseowl.splitride.authentication.domain.AuthenticationService
import com.wiseowl.splitride.authentication.domain.LoginResult
import com.wiseowl.splitride.authentication.domain.RegistrationResult
import com.wiseowl.splitride.core.network.ApiService
import com.wiseowl.splitride.core.network.EndPoint
import com.wiseowl.splitride.core.storage.AuthenticationStorage

class AuthenticationRepositoryImpl(
    val apiService: ApiService,
    val authenticationStorage: AuthenticationStorage,
) : AuthenticationService {

    override suspend fun login(
        email: String,
        password: String,
    ): LoginResult {
        try {
            val response = apiService.post<LoginResponseDTO, Map<String, String>>(
                endPoint = EndPoint.Login,
                body = mapOf(
                    "email" to email,
                    "password" to password
                )
            )
            if (response.success) {
                val accessToken = response.data!!.accessToken
                val refreshToken = response.data.refreshToken
                authenticationStorage.saveToken(accessToken)
                authenticationStorage.saveRefreshToken(refreshToken)

                return LoginResult.Success
            } else return LoginResult.AuthenticationError
        } catch (e: Exception) {
            return LoginResult.NetworkError
        }
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): RegistrationResult {
        try {
            val response = apiService.post<Unit, Map<String, String>>(
                endPoint = EndPoint.Register,
                body = mapOf(
                    "firstName" to firstName,
                    "lastName" to lastName,
                    "email" to email,
                    "password" to password
                )
            )
            return if (response.success) RegistrationResult.Success else RegistrationResult.RegistrationError
        } catch (e: Exception) {
            return RegistrationResult.NetworkError
        }
    }
}