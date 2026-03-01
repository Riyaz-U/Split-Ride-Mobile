package com.wiseowl.splitride.authentication.domain.usecase

import com.wiseowl.splitride.authentication.domain.AuthenticationService
import com.wiseowl.splitride.authentication.domain.RegistrationResult

class RegistrationUseCase(
    private val authenticationService: AuthenticationService
) {
    suspend operator fun invoke(firstName: String, lastName: String, email: String, password: String): RegistrationResult {
        val response = authenticationService.register(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password
        )
        return response
    }
}