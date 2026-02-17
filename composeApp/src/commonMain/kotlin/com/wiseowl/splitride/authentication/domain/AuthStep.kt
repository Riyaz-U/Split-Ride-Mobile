package com.wiseowl.splitride.authentication.domain

import com.wiseowl.splitride.authentication.domain.models.Input

sealed interface AuthStep{
    data class InputRequirement(val id: String, val inputs: List<Input>): AuthStep{
        fun advance(result: List<InputResponse>): AuthStep {
            return result.get()
        }
    }
}

class InputResponse(
    val id: String,
    val value: String
)