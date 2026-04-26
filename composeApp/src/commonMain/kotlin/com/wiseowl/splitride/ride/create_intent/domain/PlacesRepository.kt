package com.wiseowl.splitride.ride.create_intent.domain

import com.wiseowl.splitride.ride.create_intent.domain.model.PlaceSuggestion

interface PlacesRepository {
    suspend fun getAutocompleteSuggestions(query: String): AutocompleteResult
    suspend fun getPlaceDetails(placeId: String): PlaceDetailsResult
}

sealed class AutocompleteResult {
    data class Success(val suggestions: List<PlaceSuggestion>) : AutocompleteResult()
    object NetworkError : AutocompleteResult()
}

sealed class PlaceDetailsResult {
    data class Success(val lat: Double, val lng: Double) : PlaceDetailsResult()
    object NotFound : PlaceDetailsResult()
    object NetworkError : PlaceDetailsResult()
}
