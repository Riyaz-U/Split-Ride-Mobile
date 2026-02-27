package com.wiseowl.splitride.authentication.domain

import com.wiseowl.splitride.authentication.domain.models.Constraint

object InputValidator{
    fun validate(value: String, constraints: List<Constraint>): InputValidationResult{
        for(constraint in constraints){
            when(constraint){
                is Constraint.NonEmpty -> if(value.isEmpty()) return InputValidationResult.EMPTY
                is Constraint.MinLength -> if(value.length < constraint.length) return InputValidationResult.TOO_SHORT
                is Constraint.MaxLength -> if(value.length > constraint.length) return InputValidationResult.TOO_LONG
                is Constraint.MatchRegex -> if(!Regex(constraint.regex).matches(value)) return InputValidationResult.INVALID_FORMAT
            }
        }
        return InputValidationResult.SUCCESS
    }

    enum class InputValidationResult{
        SUCCESS,
        EMPTY,
        TOO_SHORT,
        TOO_LONG,
        INVALID_FORMAT
    }
}