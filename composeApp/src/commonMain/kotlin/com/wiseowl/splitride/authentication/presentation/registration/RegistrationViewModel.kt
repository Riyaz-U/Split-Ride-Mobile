package com.wiseowl.splitride.authentication.presentation.registration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class RegistrationViewModel: ViewModel() {
    val state = MutableStateFlow(RegistrationState())
}