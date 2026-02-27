package com.wiseowl.splitride.authentication.domain.usecase

import com.wiseowl.splitride.authentication.domain.AuthenticationService
import com.wiseowl.splitride.authentication.domain.InputValidator
import com.wiseowl.splitride.authentication.domain.models.Input
import com.wiseowl.splitride.authentication.domain.models.inputConstraints

class RegisterUseCase(
    private val authenticationService: AuthenticationService,
) {
    suspend operator fun invoke(firstName: String, lastName: String, email: String, password: String): Result<Boolean> {
        val isFirstNameValid = InputValidator.validate(
            value = firstName,
            constraints = inputConstraints[Input.FIRST_NAME]!!
        )
        if(isFirstNameValid != InputValidator.InputValidationResult.SUCCESS) return Result.failure(Exception("Email is not valid"))

        val isLastNameValid = InputValidator.validate(
            value = email,
            constraints = inputConstraints[Input.LAST_NAME]!!
        )
        if(isLastNameValid != InputValidator.InputValidationResult.SUCCESS) return Result.failure(Exception("Email is not valid"))

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

        val response = runCatching { authenticationService.register(
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password
        ).getOrThrow() }
        return response
    }
}