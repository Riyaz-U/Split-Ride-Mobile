package com.wiseowl.splitride.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.theme.interFontFamily
import com.wiseowl.splitride.core.ui.models.InputFieldState
import com.wiseowl.splitride.core.ui.models.TextState

// 1. Primary Input — outlined with label, error support
@Composable
fun PrimaryInputField(
    modifier: Modifier = Modifier,
    state: InputFieldState,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val iconTint = if (isFocused) AppColors.Primary else AppColors.TextMuted
    val hasError = state.error != null
    val containerColor = if (hasError) AppColors.InputErrorBackground else AppColors.InputBackground
    val borderColor = if (hasError) AppColors.InputErrorBorder else if (isFocused) AppColors.InputFocusedBorder else AppColors.InputUnfocusedBorder

    Column(modifier = modifier) {
        if (state.label != null) {
            Caption(state = TextState(text = state.label, color = AppColors.TextSecondary))
            Spacer(modifier = Modifier.height(6.dp))
        }
        OutlinedTextField(
            value = state.value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = state.enabled,
            interactionSource = interactionSource,
            textStyle = TextStyle(
                fontFamily = interFontFamily(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = AppColors.TextPrimary
            ),
            placeholder = {
                Text(
                    text = state.placeholder,
                    fontFamily = interFontFamily(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = AppColors.TextMuted
                )
            },
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }
            } else null,
            trailingIcon = if (trailingIcon != null) {
                {
                    if (onTrailingIconClick != null) {
                        IconButton(onClick = onTrailingIconClick) {
                            Icon(
                                imageVector = trailingIcon,
                                contentDescription = null,
                                tint = iconTint,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    } else {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = iconTint,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            } else null,
            prefix = if (state.prefix != null) {
                {
                    Text(
                        text = state.prefix,
                        fontFamily = interFontFamily(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = if (isFocused) AppColors.Primary else AppColors.TextMuted
                    )
                }
            } else null,
            isError = hasError,
            visualTransformation = state.visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = state.keyboardType),
            singleLine = state.singleLine,
            maxLines = state.maxLines,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                errorBorderColor = AppColors.InputErrorBorder,
                disabledBorderColor = AppColors.InputDisabledBorder,
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                errorContainerColor = AppColors.InputErrorBackground,
                disabledContainerColor = AppColors.InputBackground,
                cursorColor = AppColors.Primary
            )
        )
        if (hasError) {
            Spacer(modifier = Modifier.height(4.dp))
            Caption(state = TextState(text = state.error, color = AppColors.Error))
        }
    }
}

// 2. Password Input — with toggle visibility trailing icon
@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    state: InputFieldState,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val iconTint = if (isFocused) AppColors.Primary else AppColors.TextMuted
    val hasError = state.error != null

    Column(modifier = modifier) {
        if (state.label != null) {
            Caption(state = TextState(text = state.label, color = AppColors.TextSecondary))
            Spacer(modifier = Modifier.height(6.dp))
        }
        OutlinedTextField(
            value = state.value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = state.enabled,
            interactionSource = interactionSource,
            textStyle = TextStyle(
                fontFamily = interFontFamily(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = AppColors.TextPrimary
            ),
            placeholder = {
                Text(
                    text = state.placeholder,
                    fontFamily = interFontFamily(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = AppColors.TextMuted
                )
            },
            leadingIcon = if (leadingIcon != null) {
                {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }
            } else null,
            trailingIcon = if (trailingIcon != null) {
                {
                    IconButton(onClick = { onTrailingIconClick?.invoke() }) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = "Toggle password visibility",
                            tint = iconTint,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            } else null,
            isError = hasError,
            visualTransformation = state.visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColors.InputFocusedBorder,
                unfocusedBorderColor = AppColors.InputUnfocusedBorder,
                errorBorderColor = AppColors.InputErrorBorder,
                disabledBorderColor = AppColors.InputDisabledBorder,
                focusedContainerColor = AppColors.InputBackground,
                unfocusedContainerColor = AppColors.InputBackground,
                errorContainerColor = AppColors.InputErrorBackground,
                disabledContainerColor = AppColors.InputBackground,
                cursorColor = AppColors.Primary
            )
        )
        if (hasError) {
            Spacer(modifier = Modifier.height(4.dp))
            Caption(state = TextState(text = state.error, color = AppColors.Error))
        }
    }
}

// 3. Multiline Input — textarea style
@Composable
fun MultilineInputField(
    modifier: Modifier = Modifier,
    state: InputFieldState,
    onValueChange: (String) -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier) {
        if (state.label != null) {
            Caption(state = TextState(text = state.label, color = AppColors.TextSecondary))
            Spacer(modifier = Modifier.height(6.dp))
        }
        OutlinedTextField(
            value = state.value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = state.enabled,
            interactionSource = interactionSource,
            textStyle = TextStyle(
                fontFamily = interFontFamily(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = AppColors.TextPrimary
            ),
            placeholder = {
                Text(
                    text = state.placeholder,
                    fontFamily = interFontFamily(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = AppColors.TextMuted
                )
            },
            isError = state.error != null,
            singleLine = false,
            minLines = 3,
            maxLines = state.maxLines,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColors.InputFocusedBorder,
                unfocusedBorderColor = AppColors.InputUnfocusedBorder,
                errorBorderColor = AppColors.InputErrorBorder,
                disabledBorderColor = AppColors.InputDisabledBorder,
                focusedContainerColor = AppColors.InputBackground,
                unfocusedContainerColor = AppColors.InputBackground,
                errorContainerColor = AppColors.InputErrorBackground,
                disabledContainerColor = AppColors.InputBackground,
                cursorColor = AppColors.Primary
            )
        )
        if (state.error != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Caption(state = TextState(text = state.error, color = AppColors.Error))
        }
    }
}

// region Previews

@Preview
@Composable
private fun InputFieldsPreview() {
    Column(
        modifier = Modifier.background(AppColors.Surface).padding(16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Caption(state = TextState(text = "Default", color = AppColors.TextSecondary))
        PrimaryInputField(
            state = InputFieldState(placeholder = "Enter your name")
        )

        Caption(state = TextState(text = "With Label", color = AppColors.TextSecondary))
        PrimaryInputField(
            state = InputFieldState(label = "Email", placeholder = "you@example.com")
        )

        Caption(state = TextState(text = "With Prefix", color = AppColors.TextSecondary))
        PrimaryInputField(
            state = InputFieldState(label = "Phone", prefix = "+91", placeholder = "9876543210")
        )

        Caption(state = TextState(text = "With Value", color = AppColors.TextSecondary))
        PrimaryInputField(
            state = InputFieldState(label = "Name", value = "Riyaz", placeholder = "Enter your name")
        )

        Caption(state = TextState(text = "Error State", color = AppColors.TextSecondary))
        PrimaryInputField(
            state = InputFieldState(label = "Email", value = "invalid", placeholder = "you@example.com", error = "Invalid email address")
        )

        Caption(state = TextState(text = "Disabled", color = AppColors.TextSecondary))
        PrimaryInputField(
            state = InputFieldState(label = "Email", value = "disabled@example.com", placeholder = "you@example.com", enabled = false)
        )

        Caption(state = TextState(text = "Password", color = AppColors.TextSecondary))
        PasswordInputField(
            state = InputFieldState(label = "Password", placeholder = "Enter password")
        )

        Caption(state = TextState(text = "Multiline", color = AppColors.TextSecondary))
        MultilineInputField(
            state = InputFieldState(label = "Description", placeholder = "Enter description...", singleLine = false, maxLines = 5)
        )
    }
}

// endregion

