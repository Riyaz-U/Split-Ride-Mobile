package com.wiseowl.splitride.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiseowl.splitride.authentication.domain.InputValidator
import com.wiseowl.splitride.authentication.domain.LoginResult
import com.wiseowl.splitride.authentication.domain.models.Input
import com.wiseowl.splitride.authentication.domain.usecase.InputValidationResult
import com.wiseowl.splitride.authentication.domain.usecase.InputValidationUseCase
import com.wiseowl.splitride.authentication.domain.usecase.LoginUseCase
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.Navigation
import com.wiseowl.splitride.core.ui.routing.Screen
import com.wiseowl.splitride.core.ui.routing.SnackBar
import com.wiseowl.splitride.core.ui.routing.StateUpdater
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    eventBus: EventBus,
    private val loginUseCase: LoginUseCase,
    private val validationUseCase: InputValidationUseCase,
) : ViewModel() {
    val state = MutableStateFlow(LoginState())

    val stateUpdater = StateUpdater(eventBus) {
        on<LoginIntent.OnChangeEmail> {
            state.update {
                it.copy(email = it.email.copy(email, error = null))
            }
        }
        on<LoginIntent.OnChangePassword> {
            state.update {
                it.copy(password = it.password.copy(password, error = null))
            }
        }
        on<LoginIntent.OnClickLogin> {
            fun setCtaLoading(isLoading: Boolean) {
                state.update {
                    it.copy(
                        cta = it.cta.copy(isLoading = isLoading)
                    )
                }
            }

            setCtaLoading(true)

            val validationResult = validationUseCase(
                mapOf(
                    Input.EMAIL to state.value.email.value,
                    Input.PASSWORD to state.value.password.value
                )
            )
            viewModelScope.launch {
                when (validationResult) {
                    is InputValidationResult.Success -> {
                        val loginResult = loginUseCase(
                            email = state.value.email.value,
                            password = state.value.password.value
                        )
                        when (loginResult) {
                            LoginResult.Success -> it.processIntent(Navigation(Screen.Home))
                            LoginResult.NetworkError -> it.processIntent(SnackBar("Network error occurred. Please try again.", null))
                            LoginResult.AuthenticationError -> it.processIntent(SnackBar("Login failed. Please check your details and try again.", null))
                        }
                        setCtaLoading(false)
                    }

                    is InputValidationResult.Failure -> {
                        fun driveErrorMessageForInput(
                            input: Input,
                            validationResult: InputValidator.InputValidationResult,
                        ): String {
                            val inputName = when (input) {
                                Input.EMAIL -> state.value.email.label
                                Input.PASSWORD -> state.value.password.label
                                else -> "field"
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
                                    Input.EMAIL -> newState.copy(email = newState.email.copy(error = errorMessage))
                                    Input.PASSWORD -> newState.copy(password = newState.password.copy(error = errorMessage))
                                    else -> newState
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