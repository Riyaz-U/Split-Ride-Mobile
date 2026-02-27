package com.wiseowl.splitride.authentication.domain

import com.wiseowl.splitride.authentication.data.FakeAuthenticationService
import com.wiseowl.splitride.authentication.domain.usecase.LoginUseCase
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class LoginUseCaseTest {
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
    fun `user with valid credentials get successfully logged in`() = runBlocking {
        // Given
        val loginUseCase = LoginUseCase(authenticationService)

        // When
        val result = loginUseCase(registeredEmail, registeredEmailsPassword)

        // Then
        assertTrue(actual = result.isSuccess, message = result.exceptionOrNull()?.message)
    }

    @Test
    fun `login with inValid email get exception`() = runBlocking {
        // Given
        val loginUseCase = LoginUseCase(authenticationService)
        val invalidEmail = "invalidaemail@gmail"

        // When
        val result = loginUseCase(invalidEmail, registeredEmailsPassword)

        // Then
        assertFalse(actual = result.isSuccess, message = result.exceptionOrNull()?.message)
    }

    @Test
    fun `login with inValid password get exception`() = runBlocking {
        // Given
        val loginUseCase = LoginUseCase(authenticationService)
        val invalidPassword = "password"

        // When
        val result = loginUseCase(registeredEmail, invalidPassword)

        // Then
        assertFalse(actual = result.isSuccess, message = result.exceptionOrNull()?.message)
    }
}