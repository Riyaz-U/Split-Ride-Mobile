package com.wiseowl.splitride.ride.create_intent.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlaceDetailsResponseDTO(
    val result: PlaceResultDTO? = null,
    val status: String
)

@Serializable
data class PlaceResultDTO(
    val geometry: PlaceGeometryDTO
)

@Serializable
data class PlaceGeometryDTO(
    val location: LocationDTO
)

@Serializable
data class LocationDTO(
    val lat: Double,
    val lng: Double
)
