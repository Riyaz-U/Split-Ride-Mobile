package com.wiseowl.splitride.ride.create_intent.domain.model

data class PlaceSuggestion(
    val placeId: String,
    val displayName: String,
    val secondaryText: String = ""
)
