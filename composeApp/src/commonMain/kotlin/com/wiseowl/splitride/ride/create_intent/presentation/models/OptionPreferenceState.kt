package com.wiseowl.splitride.ride.create_intent.presentation.models

import com.wiseowl.splitride.core.ui.models.TextState

data class OptionPreferenceState(
    val title: TextState = TextState("Option Preferences"),
    val timing: TimingPreferenceState = TimingPreferenceState(),
    val maxGroupSize: Int = 3
)

data class TimingPreferenceState(
    val title: TextState = TextState("Flexible Timing"),
    val subtitle: TextState = TextState("+/- 15 minutes window"),
    val isChecked: Boolean = true
)
