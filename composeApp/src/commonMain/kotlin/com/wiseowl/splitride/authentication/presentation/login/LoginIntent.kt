package com.wiseowl.splitride.authentication.presentation.login

import com.wiseowl.splitride.core.ui.routing.Intent

sealed class LoginIntent: Intent() {
    data class OnChangeEmail(val email: String) : LoginIntent()
    data class OnChangePassword(val password: String) : LoginIntent()
    object OnClickLogin : LoginIntent()
}