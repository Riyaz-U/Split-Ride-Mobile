package com.wiseowl.splitride.core.ui.models

import com.wiseowl.splitride.core.ui.routing.Intent

data class ButtonState(
    val text: String,
    val intent: Intent? = null,
    val isLoading: Boolean = false,
    val enabled: Boolean = true
)
