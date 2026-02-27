package com.wiseowl.splitride.authentication.domain.usecase

import com.wiseowl.splitride.authentication.domain.AuthenticationService
import com.wiseowl.splitride.authentication.domain.InputValidator
import com.wiseowl.splitride.authentication.domain.models.Input
import com.wiseowl.splitride.authentication.domain.models.inputConstraints

class LoginUseCase(
    private val authenticationService: AuthenticationService,
) {
    suspend operator fun invoke(email: String, password: String): Result<Boolean> {
        val isEmailValid = InputValidator.validate(
            value = email,
            constraints = inputConstraints[Input.EMAIL]!!
        )
        if(isEmailValid != InputValidator.InputValidationResult.SUCCESS) return Result.failure(Exception("Email is not valid"))

        val isPasswordValid = InputValidator.validate(
            value = password,
            constraints = inputConstraints[Input.PASSWORD]!!
        )
        if(isPasswordValid != InputValidator.InputValidationResult.SUCCESS) return Result.failure(Exception("Password is not valid"))

        val response = runCatching { authenticationService.login(email, password).getOrThrow() }
        return response
    }
}