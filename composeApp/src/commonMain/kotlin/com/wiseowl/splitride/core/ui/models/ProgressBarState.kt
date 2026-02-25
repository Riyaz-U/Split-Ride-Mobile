package com.wiseowl.splitride.core.ui.models

data class ProgressBarState(
    val progress: Float = 0f,
    val totalSteps: Int = 1,
    val currentStep: Int = 0
)

