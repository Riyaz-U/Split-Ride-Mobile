package com.wiseowl.splitride.authentication.domain.models

sealed class Constraint {
    object NonEmpty : Constraint()
    data class MinLength(val length: Int) : Constraint()
    data class MaxLength(val length: Int) : Constraint()
    class MatchRegex(val regex: String) : Constraint()
}