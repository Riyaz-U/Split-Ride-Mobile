package com.wiseowl.splitride.ride.create_intent.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.Navigation
import com.wiseowl.splitride.core.ui.routing.Screen
import com.wiseowl.splitride.core.ui.routing.SnackBar
import com.wiseowl.splitride.core.ui.routing.StateUpdater
import com.wiseowl.splitride.ride.common.domain.CreateRideIntentResult
import com.wiseowl.splitride.ride.common.domain.RideRepository
import com.wiseowl.splitride.ride.common.domain.model.ScheduleType
import com.wiseowl.splitride.ride.create_intent.domain.AutocompleteResult
import com.wiseowl.splitride.ride.create_intent.domain.PlaceDetailsResult
import com.wiseowl.splitride.ride.create_intent.domain.PlacesRepository
import kotlin.time.Instant
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class CreateIntentViewModel(
    eventBus: EventBus,
    private val rideRepository: RideRepository,
    private val placesRepository: PlacesRepository
) : ViewModel() {

    val state: MutableStateFlow<CreateIntentState> = MutableStateFlow(CreateIntentState())

    private val originQueryFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)
    private val destinationQueryFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

    val stateUpdater = StateUpdater(eventBus) {
        on<CreateIntent.OnChangeStartLocation> {
            state.update { s ->
                s.copy(
                    routeDetails = s.routeDetails.copy(
                        origin = s.routeDetails.origin.copy(
                            input = s.routeDetails.origin.input.copy(value = startLocation),
                            selectedLat = null,
                            selectedLng = null
                        )
                    )
                )
            }
            originQueryFlow.tryEmit(startLocation)
        }
        on<CreateIntent.OnChangeDestination> {
            state.update { s ->
                s.copy(
                    routeDetails = s.routeDetails.copy(
                        destination = s.routeDetails.destination.copy(
                            input = s.routeDetails.destination.input.copy(value = destination),
                            selectedLat = null,
                            selectedLng = null
                        )
                    )
                )
            }
            destinationQueryFlow.tryEmit(destination)
        }
        on<CreateIntent.OnSelectOriginSuggestion> {
            val captured = suggestion
            viewModelScope.launch {
                val result = placesRepository.getPlaceDetails(captured.placeId)
                if (result is PlaceDetailsResult.Success) {
                    state.update { s ->
                        s.copy(
                            routeDetails = s.routeDetails.copy(
                                origin = s.routeDetails.origin.copy(
                                    input = s.routeDetails.origin.input.copy(value = captured.displayName),
                                    suggestions = emptyList(),
                                    selectedLat = result.lat,
                                    selectedLng = result.lng
                                )
                            )
                        )
                    }
                }
            }
        }
        on<CreateIntent.OnSelectDestinationSuggestion> {
            val captured = suggestion
            viewModelScope.launch {
                val result = placesRepository.getPlaceDetails(captured.placeId)
                if (result is PlaceDetailsResult.Success) {
                    state.update { s ->
                        s.copy(
                            routeDetails = s.routeDetails.copy(
                                destination = s.routeDetails.destination.copy(
                                    input = s.routeDetails.destination.input.copy(value = captured.displayName),
                                    suggestions = emptyList(),
                                    selectedLat = result.lat,
                                    selectedLng = result.lng
                                )
                            )
                        )
                    }
                }
            }
        }
        on<CreateIntent.OnChangeDate> {
            state.update { s ->
                s.copy(schedule = s.schedule.copy(date = s.schedule.date.copy(value = date)))
            }
        }
        on<CreateIntent.OnChangeTime> {
            state.update { s ->
                s.copy(schedule = s.schedule.copy(time = s.schedule.time.copy(value = time)))
            }
        }
        on<CreateIntent.OnToggleFlexibleTiming> {
            state.update { s ->
                s.copy(
                    preferenceState = s.preferenceState.copy(
                        timing = s.preferenceState.timing.copy(isChecked = isChecked)
                    )
                )
            }
        }
        on<CreateIntent.OnChangeMaxGroupSize> {
            state.update { s ->
                s.copy(preferenceState = s.preferenceState.copy(maxGroupSize = maxGroupSize))
            }
        }
        on<CreateIntent.OnClickCreateIntent> {
            val updater = it
            viewModelScope.launch {
                val currentState = state.value
                val origin = currentState.routeDetails.origin
                val destination = currentState.routeDetails.destination

                if (origin.selectedLat == null || origin.selectedLng == null) {
                    updater.processIntent(SnackBar("Please select a start location from the suggestions.", null))
                    return@launch
                }
                if (destination.selectedLat == null || destination.selectedLng == null) {
                    updater.processIntent(SnackBar("Please select a destination from the suggestions.", null))
                    return@launch
                }

                state.update { s -> s.copy(cta = s.cta.copy(isLoading = true)) }

                val dateValue = currentState.schedule.date.value
                val timeValue = currentState.schedule.time.value
                val isScheduled = dateValue.isNotBlank() && timeValue.isNotBlank()
                val scheduleType = if (isScheduled) ScheduleType.FLEXIBLE else ScheduleType.IMMEDIATE
                val startTime = if (isScheduled) Instant.parse("${dateValue}T${timeValue}:00Z") else null
                val flexibleMinutes = if (currentState.preferenceState.timing.isChecked) 15 else 0

                val response = rideRepository.scheduleRideIntentSearch(
                    sourceLat = origin.selectedLat,
                    sourceLng = origin.selectedLng,
                    destinationLat = destination.selectedLat,
                    destinationLng = destination.selectedLng,
                    scheduleType = scheduleType,
                    startTime = startTime,
                    flexibleMinutes = flexibleMinutes
                )

                when (response) {
                    is CreateRideIntentResult.Success -> updater.processIntent(Navigation(Screen.Home))
                    is CreateRideIntentResult.InvalidInput -> updater.processIntent(SnackBar(response.message, null))
                    CreateRideIntentResult.NetworkError -> updater.processIntent(SnackBar("Network error occurred. Please try again.", null))
                    CreateRideIntentResult.AuthenticationError -> updater.processIntent(SnackBar("Authentication failed. Please log in again.", null))
                    CreateRideIntentResult.UnknownError -> updater.processIntent(SnackBar("An unexpected error occurred. Please try again.", null))
                }

                state.update { s -> s.copy(cta = s.cta.copy(isLoading = false)) }
            }
        }
    }

    init {
        viewModelScope.launch {
            originQueryFlow.debounce(300).collectLatest { query ->
                if (query.length >= 2) {
                    val result = placesRepository.getAutocompleteSuggestions(query)
                    if (result is AutocompleteResult.Success) {
                        state.update { s ->
                            s.copy(
                                routeDetails = s.routeDetails.copy(
                                    origin = s.routeDetails.origin.copy(suggestions = result.suggestions)
                                )
                            )
                        }
                    }
                } else {
                    state.update { s ->
                        s.copy(
                            routeDetails = s.routeDetails.copy(
                                origin = s.routeDetails.origin.copy(suggestions = emptyList())
                            )
                        )
                    }
                }
            }
        }
        viewModelScope.launch {
            destinationQueryFlow.debounce(300).collectLatest { query ->
                if (query.length >= 2) {
                    val result = placesRepository.getAutocompleteSuggestions(query)
                    if (result is AutocompleteResult.Success) {
                        state.update { s ->
                            s.copy(
                                routeDetails = s.routeDetails.copy(
                                    destination = s.routeDetails.destination.copy(suggestions = result.suggestions)
                                )
                            )
                        }
                    }
                } else {
                    state.update { s ->
                        s.copy(
                            routeDetails = s.routeDetails.copy(
                                destination = s.routeDetails.destination.copy(suggestions = emptyList())
                            )
                        )
                    }
                }
            }
        }
    }
}
