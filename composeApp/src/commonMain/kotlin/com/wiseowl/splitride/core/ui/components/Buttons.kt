package com.wiseowl.splitride.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wiseowl.splitride.core.ui.models.ButtonState
import com.wiseowl.splitride.core.ui.models.TextState
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.LocalStateUpdater

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    button: ButtonState,
    onClick: (() -> Unit)? = null
){
    val isDisabled = !button.enabled || button.isLoading
    val textColor = if (isDisabled) AppColors.PrimaryDisabledContent else Color.White
    val stateUpdater = LocalStateUpdater.current

    Button(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.Primary,
            disabledContainerColor = AppColors.PrimaryDisabled
        ),
        shapes = ButtonDefaults.shapes(shape = RoundedCornerShape(12.dp)),
        enabled = !isDisabled,
        onClick = {
            if(onClick!=null) onClick()
            stateUpdater.processIntent(button.intent)
        },
    ){
        if(button.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = AppColors.PrimaryDisabledContent,
                strokeWidth = 2.dp
            )
            Subheading(state = TextState(text = button.text, color = Color.White), modifier = Modifier.padding(start = 8.dp))
        } else {
            Subheading(state = TextState(text = button.text, color = textColor))
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    button: ButtonState,
    onClick: (() -> Unit)? = null
){
    val isDisabled = !button.enabled || button.isLoading
    val borderColor = if (isDisabled) AppColors.SecondaryDisabledContent else AppColors.PrimaryTint
    val textColor = if (isDisabled) AppColors.SecondaryDisabledContent else AppColors.Primary

    Button(
        modifier = modifier.border(1.dp, borderColor, RoundedCornerShape(12.dp)),
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.SecondaryContainer,
            disabledContainerColor = AppColors.SecondaryDisabled
        ),
        shapes = ButtonDefaults.shapes(shape = RoundedCornerShape(12.dp)),
        enabled = !isDisabled,
        onClick = {
            if(onClick!=null) onClick() else { /** Handle using some reducer holder */ }
        },
    ){
        if(button.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = AppColors.SecondaryDisabledContent,
                strokeWidth = 2.dp
            )
            Spacer(Modifier.width(10.dp))
            Subheading(state = TextState(text = button.text, color = textColor))
        } else Subheading(state = TextState(text = button.text, color = textColor))
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun GhostButton(
    modifier: Modifier = Modifier,
    button: ButtonState,
    onClick: (() -> Unit)? = null
){
    val textColor = if (!button.enabled) AppColors.PrimaryDisabled else AppColors.Primary

    Button(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        shapes = ButtonDefaults.shapes(shape = RoundedCornerShape(12.dp)),
        elevation = null,
        enabled = button.enabled && !button.isLoading,
        onClick = {
            if(onClick!=null) onClick() else { /** Handle using some reducer holder */ }
        },
    ){
        if(button.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = AppColors.Primary,
                strokeWidth = 2.dp
            )
        } else {
            Subheading(state = TextState(text = button.text, color = textColor))
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DestructiveButton(
    modifier: Modifier = Modifier,
    button: ButtonState,
    icon: ImageVector? = null,
    onClick: (() -> Unit)? = null
){
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.Error,
            disabledContainerColor = AppColors.ErrorDisabled
        ),
        shapes = ButtonDefaults.shapes(shape = RoundedCornerShape(12.dp)),
        enabled = button.enabled && !button.isLoading,
        onClick = {
            if(onClick!=null) onClick() else { /** Handle using some reducer holder */ }
        },
    ){
        if(button.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
            Subheading(modifier = Modifier.padding(start = 8.dp), state = TextState(text = button.text, color = Color.White))
        } else {
            if(icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Subheading(state = TextState(text = button.text, color = Color.White))
        }
    }
}

// region Previews

@Preview
@Composable
private fun PrimaryButtonPreview() {
    Column(
        modifier = Modifier.background(AppColors.Surface).padding(16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PrimaryButton(button = ButtonState(text = "PRIMARY"))
        PrimaryButton(button = ButtonState(text = "LOADING", isLoading = true))
        PrimaryButton(button = ButtonState(text = "DISABLED", enabled = false))
    }
}

@Preview
@Composable
private fun SecondaryButtonPreview() {
    Column(
        modifier = Modifier.background(AppColors.Surface).padding(16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SecondaryButton(button = ButtonState(text = "SECONDARY"))
        SecondaryButton(button = ButtonState(text = "LOADING", isLoading = true))
        SecondaryButton(button = ButtonState(text = "DISABLED", enabled = false))
    }
}

@Preview
@Composable
private fun GhostButtonPreview() {
    Column(
        modifier = Modifier.background(AppColors.Surface).padding(16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        GhostButton(button = ButtonState(text = "GHOST"))
        GhostButton(button = ButtonState(text = "LOADING", isLoading = true))
        GhostButton(button = ButtonState(text = "DISABLED", enabled = false))
    }
}

@Preview
@Composable
private fun DestructiveButtonPreview() {
    Column(
        modifier = Modifier.background(AppColors.Surface).padding(16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DestructiveButton(button = ButtonState(text = "DELETE"))
        DestructiveButton(button = ButtonState(text = "DELETING", isLoading = true))
        DestructiveButton(button = ButtonState(text = "DISABLED", enabled = false))
    }
}

// endregion

