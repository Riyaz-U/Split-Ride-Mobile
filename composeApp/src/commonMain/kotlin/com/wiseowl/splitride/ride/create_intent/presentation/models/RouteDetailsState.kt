package com.wiseowl.splitride.ride.create_intent.presentation.models

import com.wiseowl.splitride.core.ui.models.InputFieldState
import com.wiseowl.splitride.core.ui.models.TextState

data class RouteDetailsState(
    val title: TextState = TextState("Route Details"),
    val origin: LocationFieldState = LocationFieldState(input = InputFieldState(label = "Start Location", placeholder = "Home")),
    val destination: LocationFieldState = LocationFieldState(input = InputFieldState(label = "Destination", placeholder = "Nearest Metro Station"))
)
