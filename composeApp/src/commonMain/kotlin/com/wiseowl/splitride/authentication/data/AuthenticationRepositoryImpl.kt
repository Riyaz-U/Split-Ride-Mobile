package com.wiseowl.splitride.authentication.data

import com.wiseowl.splitride.authentication.data.dto.LoginResponseDTO
import com.wiseowl.splitride.authentication.domain.AuthenticationRepository
import com.wiseowl.splitride.authentication.domain.models.LoginResult
import com.wiseowl.splitride.authentication.domain.models.RegistrationResult
import com.wiseowl.splitride.authentication.domain.models.UserCredential
import com.wiseowl.splitride.core.network.ApiService
import com.wiseowl.splitride.core.network.EndPoint

class AuthenticationRepositoryImpl(
    val apiService: ApiService
): AuthenticationRepository {
    override suspend fun login(credentials: UserCredential): LoginResult {
        val response = apiService.get<LoginResponseDTO>(
            EndPoint.Login,
            "credential" to when(credentials){
                is UserCredential.EmailPassword -> credentials.email
                is UserCredential.MobilePassword -> credentials.mobileNumber
                is UserCredential.UsernamePassword -> credentials.username
            },
            "password" to when(credentials){
                is UserCredential.EmailPassword -> credentials.password
                is UserCredential.MobilePassword -> credentials.password
                is UserCredential.UsernamePassword -> credentials.password
            }
        )
        return LoginResult.SUCCESS
    }

    override suspend fun register(
        name: String,
        credentials: UserCredential,
    ): RegistrationResult {
        TODO("Not yet implemented")
    }

}