package com.wiseowl.splitride.ride.home.presentation.models

import com.wiseowl.splitride.core.ui.models.ButtonState
import com.wiseowl.splitride.core.ui.models.TextState
import com.wiseowl.splitride.ride.home.presentation.HomeIntent

data class CreateRideCardState(
    val title: TextState = TextState("Create Ride Intent"),
    val description: TextState = TextState("Start your journey and let\n" + "others join you."),
    val cta: ButtonState = ButtonState(
        text =  "Start Now",
        intent = HomeIntent.OnClickRideIntent
    )
)
