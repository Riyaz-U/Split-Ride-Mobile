package com.wiseowl.splitride.authentication.domain.models

enum class Input {
    EMAIL,
    PASSWORD,
    FIRST_NAME,
    LAST_NAME
}

val inputConstraints: Map<Input, List<Constraint>> = mapOf(
    Input.EMAIL to listOf(
        Constraint.NonEmpty,
        Constraint.MatchRegex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    ),
    Input.PASSWORD to listOf(
        Constraint.NonEmpty,
        Constraint.MinLength(8)
    ),
    Input.FIRST_NAME to listOf(
        Constraint.NonEmpty,
        Constraint.MaxLength(50)
    ),
    Input.LAST_NAME to listOf(
        Constraint.NonEmpty,
        Constraint.MaxLength(50)
    )
)