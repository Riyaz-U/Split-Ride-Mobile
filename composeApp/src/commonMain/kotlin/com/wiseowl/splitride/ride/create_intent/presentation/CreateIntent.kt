package com.wiseowl.splitride.ride.create_intent.presentation

import com.wiseowl.splitride.core.ui.routing.Intent
import com.wiseowl.splitride.ride.create_intent.domain.model.PlaceSuggestion

sealed class CreateIntent : Intent() {
    data class OnChangeStartLocation(val startLocation: String) : CreateIntent()
    data class OnChangeDestination(val destination: String) : CreateIntent()
    data class OnChangeDate(val date: String) : CreateIntent()
    data class OnChangeTime(val time: String) : CreateIntent()
    data class OnToggleFlexibleTiming(val isChecked: Boolean) : CreateIntent()
    data class OnChangeMaxGroupSize(val maxGroupSize: Int) : CreateIntent()
    data class OnSelectOriginSuggestion(val suggestion: PlaceSuggestion) : CreateIntent()
    data class OnSelectDestinationSuggestion(val suggestion: PlaceSuggestion) : CreateIntent()
    object OnClickCreateIntent : CreateIntent()
}
