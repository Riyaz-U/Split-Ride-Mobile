package com.wiseowl.splitride.onboarding.presentation.models

import com.wiseowl.splitride.core.ui.models.TextState
import org.jetbrains.compose.resources.DrawableResource

data class OnboardingPage(
    val title: TextState,
    val description: TextState,
    val imageRes: DrawableResource
)