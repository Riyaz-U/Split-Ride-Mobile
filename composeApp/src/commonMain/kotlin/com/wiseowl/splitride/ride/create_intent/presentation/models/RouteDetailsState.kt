package com.wiseowl.splitride.ride.create_intent.presentation.models

import com.wiseowl.splitride.core.ui.models.InputFieldState
import com.wiseowl.splitride.core.ui.models.TextState

data class RouteDetailsState(
    val title: TextState = TextState("Route Details"),
    val origin: InputFieldState = InputFieldState(label = "Start Location", placeholder =  "Home"),
    val destination: InputFieldState = InputFieldState(label = "Destination", placeholder = "Nearest Metro Station")
)
