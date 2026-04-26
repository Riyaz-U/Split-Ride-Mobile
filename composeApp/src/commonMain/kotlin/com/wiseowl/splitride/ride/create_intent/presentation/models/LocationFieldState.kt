package com.wiseowl.splitride.ride.create_intent.presentation.models

import com.wiseowl.splitride.core.ui.models.InputFieldState
import com.wiseowl.splitride.ride.create_intent.domain.model.PlaceSuggestion

data class LocationFieldState(
    val input: InputFieldState = InputFieldState(),
    val suggestions: List<PlaceSuggestion> = emptyList(),
    val selectedLat: Double? = null,
    val selectedLng: Double? = null
)
