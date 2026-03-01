package com.wiseowl.splitride.authentication.presentation.registration

import com.wiseowl.splitride.core.ui.routing.Intent

sealed class RegistrationIntent: Intent() {
    object OnClickRegister : RegistrationIntent()
    data class OnChangeFirstName(val firstName: String) : RegistrationIntent()
    data class OnChangeLastName(val lastName: String) : RegistrationIntent()
    data class OnChangeEmail(val email: String) : RegistrationIntent()
    data class OnChangePassword(val password: String) : RegistrationIntent()
    data class OnChangeConfirmedPassword(val password: String) : RegistrationIntent()
    data class OnClickTermsCheckbox(val isChecked: Boolean) : RegistrationIntent()
}