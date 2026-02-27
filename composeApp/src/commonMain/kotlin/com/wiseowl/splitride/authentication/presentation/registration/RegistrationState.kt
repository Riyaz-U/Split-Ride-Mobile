package com.wiseowl.splitride.authentication.presentation.registration

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.models.ButtonState
import com.wiseowl.splitride.core.ui.models.InputFieldState
import com.wiseowl.splitride.core.ui.models.SpanTextState
import com.wiseowl.splitride.core.ui.models.TextState

@Immutable
data class RegistrationState(
    val title: TextState = TextState("Join Split Ride", Color.Black),
    val subtitle: TextState = TextState("Pool rides, split costs, save time.", Color.Gray), //#616F89
    val firstName: InputFieldState = InputFieldState(
        placeholder = "John",
        label = "First Name",
        keyboardType = androidx.compose.ui.text.input.KeyboardType.Email
    ),
    val lastName: InputFieldState = InputFieldState(
        placeholder = "Doe",
        label = "Last Name",
        keyboardType = androidx.compose.ui.text.input.KeyboardType.Email
    ),
    val email: InputFieldState = InputFieldState(
        placeholder = "name@email.com",
        label = "Email",
        keyboardType = androidx.compose.ui.text.input.KeyboardType.Email
    ),
    val password: InputFieldState = InputFieldState(
        placeholder = "At least 8 characters",
        label = "Password",
        keyboardType = androidx.compose.ui.text.input.KeyboardType.Email
    ),
    val confirmPassword: InputFieldState = InputFieldState(
        placeholder = "Re-enter your password",
        label = "Password Confirmation",
        keyboardType = androidx.compose.ui.text.input.KeyboardType.Email
    ),
    val termsText: SpanTextState = SpanTextState(
        spans = listOf(
            com.wiseowl.splitride.core.ui.models.TextSpan(
                text = "I agree to the ",
                color = Color.Black
            ),
            com.wiseowl.splitride.core.ui.models.TextSpan(
                text = "Terms of Service",
                color = AppColors.Primary,
                intent = null, // TODO: Add navigation intent
                weight = FontWeight.Bold
            ),
            com.wiseowl.splitride.core.ui.models.TextSpan(
                text = " and ",
                color = Color.Black
            ),
            com.wiseowl.splitride.core.ui.models.TextSpan(
                text = "Privacy Policy",
                color = AppColors.Primary,
                intent = null, // TODO: Add navigation intent
                weight = FontWeight.Bold
            ),
            com.wiseowl.splitride.core.ui.models.TextSpan(
                text = ".",
                color = AppColors.Primary
            )
        )
    ),
    val termsAccepted: Boolean = false,
    val cta: ButtonState = ButtonState(
        text = "Create Account",
        intent = null
    ),
    val alreadyHaveAccountText: SpanTextState = SpanTextState(
        spans = listOf(
            com.wiseowl.splitride.core.ui.models.TextSpan(
                text = "Already have an account? ",
                color = Color.Gray
            ),
            com.wiseowl.splitride.core.ui.models.TextSpan(
                text = "Log in",
                color = AppColors.TextPrimary,
                intent = null, // TODO: Add navigation intent
                weight = FontWeight.Bold
            )
        )
    )
)
