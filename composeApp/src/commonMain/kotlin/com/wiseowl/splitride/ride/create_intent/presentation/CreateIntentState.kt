package com.wiseowl.splitride.ride.create_intent.presentation

import com.wiseowl.splitride.ride.create_intent.presentation.models.OptionPreferenceState
import com.wiseowl.splitride.ride.create_intent.presentation.models.RouteDetailsState
import com.wiseowl.splitride.ride.create_intent.presentation.models.ScheduleState

data class CreateIntentState(
    val routeDetails: RouteDetailsState = RouteDetailsState(),
    val schedule: ScheduleState = ScheduleState(),
    val preferenceState: OptionPreferenceState = OptionPreferenceState()
)
