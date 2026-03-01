package com.wiseowl.splitride.authentication.data

import com.wiseowl.splitride.authentication.domain.AuthenticationService
import com.wiseowl.splitride.authentication.domain.LoginResult
import com.wiseowl.splitride.authentication.domain.RegistrationResult

class FakeAuthenticationService(
    private val users: Map<String, String> = mapOf() // email to password mapping
): AuthenticationService {

    override suspend fun login(
        email: String,
        password: String,
    ): LoginResult {
        return if(users.containsKey(email) && users[email] == password) LoginResult.Success
        else LoginResult.AuthenticationError
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): RegistrationResult {
        if(users[email] == null) {
            users.plus(email to password)
            return RegistrationResult.Success
        } else return RegistrationResult.RegistrationError
    }
}