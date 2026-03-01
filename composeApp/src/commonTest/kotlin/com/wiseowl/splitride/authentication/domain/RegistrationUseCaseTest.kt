package com.wiseowl.splitride.authentication.domain

import com.wiseowl.splitride.authentication.data.FakeAuthenticationService
import com.wiseowl.splitride.authentication.domain.usecase.RegistrationUseCase
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue


class RegistrationUseCaseTest {
    lateinit var authenticationService: AuthenticationService
    val registeredEmail = "interested.user@gmail.com"
    val registeredEmailsPassword = "password123"

    @BeforeTest
    fun setup(){
        authenticationService = FakeAuthenticationService(
            users = mapOf(registeredEmail to registeredEmailsPassword)
        )
    }

    @Test
    fun `registration with new mail returns success`() = runBlocking {
        // Given
        val firstName = "Riyaz"
        val lastName = "Uddin"
        val email = registeredEmail
        val password = registeredEmailsPassword + "123"
        val registrationUseCase = RegistrationUseCase(authenticationService)

        // When
        val result = registrationUseCase(firstName, lastName, email, password)

        // Then
        assertTrue(actual = result is RegistrationResult.RegistrationError, message = "result is not RegistrationError, but ${result::class.simpleName}")
    }

    @Test
    fun `registration with registered mail return error`() = runBlocking {
        // Given
        val firstName = "Riyaz"
        val lastName = "Uddin"
        val email = registeredEmail
        val password = registeredEmailsPassword + "123"
        val registrationUseCase = RegistrationUseCase(authenticationService)

        // When
        val result = registrationUseCase(firstName, lastName, email, password)

        // Then
        assertTrue(actual = result is RegistrationResult.RegistrationError, message = "result is not RegistrationError, but ${result::class.simpleName}")
    }
}