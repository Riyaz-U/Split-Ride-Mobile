package com.wiseowl.splitride.ride.create_intent.presentation.models

import com.wiseowl.splitride.core.ui.models.InputFieldState
import com.wiseowl.splitride.core.ui.models.TextState

data class ScheduleState(
    val title: TextState = TextState("Schedule"),
    val date: InputFieldState = InputFieldState(label = "Date"),
    val time: InputFieldState = InputFieldState(label = "Time"),
)
