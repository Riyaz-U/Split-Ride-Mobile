package com.wiseowl.splitride.authentication.data

import com.wiseowl.splitride.authentication.domain.AuthenticationService

class FakeAuthenticationService(
    private val users: Map<String, String> = mapOf() // email to password mapping
): AuthenticationService {

    override suspend fun login(
        email: String,
        password: String,
    ): Result<Boolean> {
        if(users.containsKey(email) && users[email] == password) {
            return Result.success(true)
        }
        return Result.failure(Exception("Invalid email or password"))
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): Result<Boolean> {
        if(users[email] == null) {
            users.plus(email to password)
            return Result.success(true)
        } else return Result.failure(Exception("User with this email already exists"))
    }
}