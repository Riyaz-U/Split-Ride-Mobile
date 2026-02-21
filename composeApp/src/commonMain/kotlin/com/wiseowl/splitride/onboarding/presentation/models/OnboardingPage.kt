package com.wiseowl.splitride.onboarding.presentation.models

import org.jetbrains.compose.resources.DrawableResource

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: DrawableResource
)