package com.wiseowl.splitride.authentication.domain.models

class Input(
    val id: String,
    val constraints: List<Constraint>,
    val securityLevel: SecurityLevel
)

class InputResponse(
    val id: String,
    val value: String
)

enum class SecurityLevel {
    LOW,
    HIGH
}

sealed class Constraint {
    object NonEmpty : Constraint()
    data class MinLength(val length: Int) : Constraint()
    data class MaxLength(val length: Int) : Constraint()
}