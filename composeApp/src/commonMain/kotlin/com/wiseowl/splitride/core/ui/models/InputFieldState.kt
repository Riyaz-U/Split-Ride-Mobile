package com.wiseowl.splitride.core.ui.models

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

data class InputFieldState(
    val value: String = "",
    val placeholder: String = "",
    val label: String? = null,
    val prefix: String? = null,
    val error: String? = null,
    val enabled: Boolean = true,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val singleLine: Boolean = true,
    val maxLines: Int = 1
)

