package com.wiseowl.splitride.authentication.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiseowl.splitride.authentication.domain.RegistrationResult
import com.wiseowl.splitride.authentication.domain.models.Input
import com.wiseowl.splitride.authentication.domain.usecase.InputValidationResult
import com.wiseowl.splitride.authentication.domain.usecase.InputValidationUseCase
import com.wiseowl.splitride.authentication.domain.usecase.RegistrationUseCase
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.Navigation
import com.wiseowl.splitride.core.ui.routing.Screen
import com.wiseowl.splitride.core.ui.routing.StateUpdater
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel(
    eventBus: EventBus,
    private val registrationUseCase: RegistrationUseCase,
    private val validationUseCase: InputValidationUseCase
): ViewModel() {
    val state = MutableStateFlow(RegistrationState())

    val stateUpdater = StateUpdater(eventBus){
        on<RegistrationIntent.OnChangeFirstName>{
            state.update {
                it.copy(firstName = it.firstName.copy(firstName))
            }
        }
        on<RegistrationIntent.OnChangeLastName>{
            state.update {
                it.copy(lastName = it.lastName.copy(lastName))
            }
        }
        on<RegistrationIntent.OnChangeEmail>{
            state.update {
                it.copy(email = it.email.copy(email))
            }
        }
        on<RegistrationIntent.OnChangePassword>{
            state.update {
                it.copy(password = it.password.copy(password))
            }
        }
        on<RegistrationIntent.OnChangeConfirmedPassword>{
            state.update {
                it.copy(confirmPassword = it.confirmPassword.copy(password))
            }
        }
        on<RegistrationIntent.OnClickTermsCheckbox>{
            state.update { it.copy(termsAccepted = isChecked) }
        }
        on<RegistrationIntent.OnClickRegister> {
            state.update {
                it.copy(
                    cta = it.cta.copy(
                        isLoading = true
                    )
                )
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
                when(validationResult){
                    is InputValidationResult.Success -> {
                        val registrationResult = registrationUseCase(
                            firstName = state.value.firstName.value,
                            lastName = state.value.lastName.value,
                            email = state.value.email.value,
                            password = state.value.password.value
                        )
                        when (registrationResult) {
                            RegistrationResult.Success -> it.processIntent(Navigation(Screen.Home))
                            RegistrationResult.NetworkError -> TODO()
                            RegistrationResult.RegistrationError -> TODO()
                        }
                    }

                    is InputValidationResult.Failure -> {
                        state.update { currentState ->
                            var newState = currentState
                            validationResult.invalidInputs.forEach { invalidInput ->
                                newState = when(invalidInput){
                                    Input.FIRST_NAME -> newState.copy(firstName = newState.firstName.copy(error = "Invalid first name"))
                                    Input.LAST_NAME -> newState.copy(lastName = newState.lastName.copy(error = "Invalid last name"))
                                    Input.EMAIL -> newState.copy(email = newState.email.copy(error = "Invalid email"))
                                    Input.PASSWORD -> newState.copy(password = newState.password.copy(error = "Invalid password"))
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