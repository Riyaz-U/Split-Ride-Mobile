package com.wiseowl.splitride.authentication.domain.usecase

import com.wiseowl.splitride.authentication.domain.AuthenticationService
import com.wiseowl.splitride.authentication.domain.LoginResult

class LoginUseCase(
    private val authenticationService: AuthenticationService,
) {
    suspend operator fun invoke(email: String, password: String): LoginResult {
        val response = authenticationService.login(email, password)
        return response
    }
}