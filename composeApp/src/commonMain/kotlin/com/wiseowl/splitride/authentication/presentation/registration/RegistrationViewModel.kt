package com.wiseowl.splitride.authentication.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiseowl.splitride.authentication.domain.InputValidator
import com.wiseowl.splitride.authentication.domain.RegistrationResult
import com.wiseowl.splitride.authentication.domain.models.Input
import com.wiseowl.splitride.authentication.domain.usecase.InputValidationResult
import com.wiseowl.splitride.authentication.domain.usecase.InputValidationUseCase
import com.wiseowl.splitride.authentication.domain.usecase.RegistrationUseCase
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.Navigation
import com.wiseowl.splitride.core.ui.routing.Screen
import com.wiseowl.splitride.core.ui.routing.SnackBar
import com.wiseowl.splitride.core.ui.routing.StateUpdater
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel(
    eventBus: EventBus,
    private val registrationUseCase: RegistrationUseCase,
    private val validationUseCase: InputValidationUseCase,
) : ViewModel() {
    val state = MutableStateFlow(RegistrationState())

    val stateUpdater = StateUpdater(eventBus) {
        on<RegistrationIntent.OnChangeFirstName> {
            state.update {
                it.copy(firstName = it.firstName.copy(firstName, error = null))
            }
        }
        on<RegistrationIntent.OnChangeLastName> {
            state.update {
                it.copy(lastName = it.lastName.copy(lastName, error = null))
            }
        }
        on<RegistrationIntent.OnChangeEmail> {
            state.update {
                it.copy(email = it.email.copy(email, error = null))
            }
        }
        on<RegistrationIntent.OnChangePassword> {
            state.update {
                it.copy(password = it.password.copy(password, error = null))
            }
        }
        on<RegistrationIntent.OnChangeConfirmedPassword> {
            state.update {
                it.copy(confirmPassword = it.confirmPassword.copy(password, error = null))
            }
        }
        on<RegistrationIntent.OnClickTermsCheckbox> {
            state.update { it.copy(termsAccepted = isChecked) }
        }
        on<RegistrationIntent.OnClickRegister> {
            fun setCtaLoading(isLoading: Boolean) {
                state.update {
                    it.copy(
                        cta = it.cta.copy(
                            isLoading = isLoading
                        )
                    )
                }
            }

            setCtaLoading(true)
            if(!state.value.termsAccepted) {
                it.processIntent(SnackBar("You must accept the terms to proceed"))
                setCtaLoading(false)
                return@on
            }

            val validationResult = validationUseCase(
                mapOf(
                    Input.FIRST_NAME to state.value.firstName.value,
                    Input.LAST_NAME to state.value.lastName.value,
                    Input.EMAIL to state.value.email.value,
                    Input.PASSWORD to state.value.password.value
                )
            )
            viewModelScope.launch {
                when (validationResult) {
                    is InputValidationResult.Success -> {
                        val registrationResult = registrationUseCase(
                            firstName = state.value.firstName.value,
                            lastName = state.value.lastName.value,
                            email = state.value.email.value,
                            password = state.value.password.value
                        )
                        when (registrationResult) {
                            RegistrationResult.Success -> it.processIntent(Navigation(Screen.Home))
                            RegistrationResult.NetworkError -> it.processIntent(SnackBar("Network error occurred. Please try again."))
                            RegistrationResult.RegistrationError -> it.processIntent(SnackBar("Registration failed. Please check your details and try again."))
                        }
                        setCtaLoading(false)
                    }

                    is InputValidationResult.Failure -> {
                        fun driveErrorMessageForInput(
                            input: Input,
                            validationResult: InputValidator.InputValidationResult,
                        ): String {
                            val inputName = when (input) {
                                Input.FIRST_NAME -> state.value.firstName.label
                                Input.LAST_NAME -> state.value.lastName.label
                                Input.EMAIL -> state.value.email.label
                                Input.PASSWORD -> state.value.password.label
                            }
                            return when (validationResult) {
                                InputValidator.InputValidationResult.EMPTY -> "$inputName cannot be empty"
                                InputValidator.InputValidationResult.TOO_SHORT -> "$inputName is too short"
                                InputValidator.InputValidationResult.TOO_LONG -> "$inputName is too long"
                                InputValidator.InputValidationResult.INVALID_FORMAT -> "Invalid format"
                                else -> "Invalid input"
                            }
                        }
                        state.update { currentState ->
                            var newState = currentState
                            validationResult.invalidInputs.forEach { invalidInput ->
                                val errorMessage = driveErrorMessageForInput(invalidInput.key, invalidInput.value)
                                newState = when (invalidInput.key) {
                                    Input.FIRST_NAME -> newState.copy(firstName = newState.firstName.copy(error = errorMessage))
                                    Input.LAST_NAME -> newState.copy(lastName = newState.lastName.copy(error = errorMessage))
                                    Input.EMAIL -> newState.copy(email = newState.email.copy(error = errorMessage))
                                    Input.PASSWORD -> newState.copy(password = newState.password.copy(error = errorMessage))
                                }
                            }
                            newState.copy(cta = newState.cta.copy(isLoading = false))
                        }
                    }
                }
            }
        }
    }
}