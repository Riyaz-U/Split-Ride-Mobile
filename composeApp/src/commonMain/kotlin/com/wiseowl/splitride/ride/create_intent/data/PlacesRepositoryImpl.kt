package com.wiseowl.splitride.ride.create_intent.data

import com.wiseowl.splitride.ride.create_intent.data.dto.AutocompleteResponseDTO
import com.wiseowl.splitride.ride.create_intent.data.dto.PlaceDetailsResponseDTO
import com.wiseowl.splitride.ride.create_intent.domain.AutocompleteResult
import com.wiseowl.splitride.ride.create_intent.domain.PlaceDetailsResult
import com.wiseowl.splitride.ride.create_intent.domain.PlacesRepository
import com.wiseowl.splitride.ride.create_intent.domain.model.PlaceSuggestion
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PlacesRepositoryImpl(
    private val apiKey: String
) : PlacesRepository {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override suspend fun getAutocompleteSuggestions(query: String): AutocompleteResult {
        return try {
            val response = client.get("https://maps.googleapis.com/maps/api/place/autocomplete/json") {
                parameter("input", query)
                parameter("key", apiKey)
            }.body<AutocompleteResponseDTO>()

            AutocompleteResult.Success(
                response.predictions.map { prediction ->
                    PlaceSuggestion(
                        placeId = prediction.placeId,
                        displayName = prediction.structuredFormatting?.mainText ?: prediction.description,
                        secondaryText = prediction.structuredFormatting?.secondaryText ?: ""
                    )
                }
            )
        } catch (e: Exception) {
            AutocompleteResult.NetworkError
        }
    }

    override suspend fun getPlaceDetails(placeId: String): PlaceDetailsResult {
        return try {
            val response = client.get("https://maps.googleapis.com/maps/api/place/details/json") {
                parameter("place_id", placeId)
                parameter("fields", "geometry")
                parameter("key", apiKey)
            }.body<PlaceDetailsResponseDTO>()

            val location = response.result?.geometry?.location
            if (response.status == "OK" && location != null) {
                PlaceDetailsResult.Success(location.lat, location.lng)
            } else {
                PlaceDetailsResult.NotFound
            }
        } catch (e: Exception) {
            PlaceDetailsResult.NetworkError
        }
    }
}
