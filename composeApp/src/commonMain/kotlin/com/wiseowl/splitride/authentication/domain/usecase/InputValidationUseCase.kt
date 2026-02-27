package com.wiseowl.splitride.authentication.domain.usecase

import com.wiseowl.splitride.authentication.domain.InputValidator
import com.wiseowl.splitride.authentication.domain.models.Input
import com.wiseowl.splitride.authentication.domain.models.inputConstraints

class InputValidationUseCase {
    operator fun invoke(
        fields: Map<Input, String>,
    ): InputValidationResult {
        val invalidFields = fields.filter { (input, value) ->
            InputValidator.validate(value, inputConstraints[input]!!) != InputValidator.InputValidationResult.SUCCESS
        }
        return if(invalidFields.isEmpty()) InputValidationResult.Failure(invalidFields.keys.toList()) else InputValidationResult.Success
    }
}

sealed class InputValidationResult{
    object Success: InputValidationResult()
    class Failure(invalidFields: List<Input>): InputValidationResult()
}