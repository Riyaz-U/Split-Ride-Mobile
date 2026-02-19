package com.wiseowl.splitride.authentication.domain

import com.wiseowl.splitride.authentication.domain.models.Constraint
import kotlin.collections.List

class InputStep(private val fields: List<FieldDescriptor>, private val onComplete: () -> Unit): AuthStep {
    override val description: StepDescription
        get() = InputStepDescription(fields)
    override val submissionHandler: SubmissionHandler
        get() = InputSubmissionHandler(fields, onComplete = onComplete)
}

class InputStepDescription(val fields: List<FieldDescriptor>): StepDescription

interface FieldDescriptor {
    val semanticKey: String
    val isSecure: Boolean
    val constraints: List<Constraint>
}

class InputSubmissionHandler(fieldDescriptors: List<FieldDescriptor>, onComplete: () -> Unit): SubmissionHandler {
    val fieldSlots: List<FieldSlot> = fieldDescriptors.map { FieldSlot(it) }
    override fun submit(): Transition {
        // Validate inputs and determine next step or failure
        val validationResults = fieldSlots.map { slot ->
            InputValidator.validate(slot.value, slot.fieldDescriptor.constraints)
        }
        val validationFailed = validationResults.any { it != InputValidator.InputValidationResult.SUCCESS } // If any validation fails, return a Failed transition
        if (validationFailed) {
            return Transition.Failed("Validation failed for one or more fields.")
        }
        return Transition.Finished
    }
}

class FieldSlot(val fieldDescriptor: FieldDescriptor, var value: String = "")

interface AuthStep {
    val description: StepDescription
    val submissionHandler: SubmissionHandler
}

interface StepDescription {

}

interface SubmissionHandler {
    fun submit(): Transition
}

sealed class Transition {
    class Next(val nextStep: AuthStep) : Transition()
    class Failed(val errorMessage: String) : Transition()
    object Finished : Transition()
}