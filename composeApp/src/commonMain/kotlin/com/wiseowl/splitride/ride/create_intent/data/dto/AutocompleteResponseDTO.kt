package com.wiseowl.splitride.ride.create_intent.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AutocompleteResponseDTO(
    val predictions: List<AutocompletePredictionDTO>,
    val status: String
)

@Serializable
data class AutocompletePredictionDTO(
    @SerialName("place_id") val placeId: String,
    val description: String,
    @SerialName("structured_formatting") val structuredFormatting: StructuredFormattingDTO? = null
)

@Serializable
data class StructuredFormattingDTO(
    @SerialName("main_text") val mainText: String,
    @SerialName("secondary_text") val secondaryText: String? = null
)
