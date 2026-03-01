package com.wiseowl.splitride.authentication.presentation.login

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.models.ButtonState
import com.wiseowl.splitride.core.ui.models.InputFieldState
import com.wiseowl.splitride.core.ui.models.SpanTextState
import com.wiseowl.splitride.core.ui.models.TextState
import com.wiseowl.splitride.core.ui.routing.Navigation
import com.wiseowl.splitride.core.ui.routing.Screen

@Immutable
data class LoginState(
    val title: TextState = TextState("Split Ride", Color.Black),
    val subtitle: TextState = TextState("Reliable ride-pooling for commuters", Color.Gray), //#616F89
    val email: InputFieldState = InputFieldState(
        placeholder = "your@email.com",
        label = "Email",
        keyboardType = androidx.compose.ui.text.input.KeyboardType.Email
    ),
    val password: InputFieldState = InputFieldState(
        placeholder = "Your password",
        label = "Password",
        keyboardType = androidx.compose.ui.text.input.KeyboardType.Email
    ),
    val cta: ButtonState = ButtonState(
        text = "Log In",
        intent = LoginIntent.OnClickLogin
    ),
    val createAccountNote: SpanTextState = SpanTextState(
        spans = listOf(
            com.wiseowl.splitride.core.ui.models.TextSpan(
                text = "New to Split Ride? ",
                color = Color.Gray
            ),
            com.wiseowl.splitride.core.ui.models.TextSpan(
                text = "Create an account",
                color = AppColors.Primary,
                intent = Navigation(Screen.Registration),
                weight = FontWeight.Medium
            )
        )
    )
)
