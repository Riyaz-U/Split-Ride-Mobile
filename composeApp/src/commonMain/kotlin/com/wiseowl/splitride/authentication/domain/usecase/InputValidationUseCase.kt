package com.wiseowl.splitride.authentication.domain.usecase

import com.wiseowl.splitride.authentication.domain.InputValidator
import com.wiseowl.splitride.authentication.domain.models.Input
import com.wiseowl.splitride.authentication.domain.models.inputConstraints

class InputValidationUseCase {
    operator fun invoke(
        fields: Map<Input, String>,
    ): InputValidationResult {
        val invalidFields = fields.map { (input, value) ->
            input to InputValidator.validate(value, inputConstraints[input]!!)
        }.filter { it.second != InputValidator.InputValidationResult.SUCCESS }.toMap()
        return if(invalidFields.isNotEmpty()) InputValidationResult.Failure(invalidFields) else InputValidationResult.Success
    }
}

sealed class InputValidationResult{
    object Success: InputValidationResult()
    class Failure(val invalidInputs: Map<Input, InputValidator.InputValidationResult>): InputValidationResult()
}