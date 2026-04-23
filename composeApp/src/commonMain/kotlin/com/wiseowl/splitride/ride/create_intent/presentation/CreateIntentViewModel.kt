package com.wiseowl.splitride.ride.create_intent.presentation

import androidx.lifecycle.ViewModel
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.SnackBar
import com.wiseowl.splitride.core.ui.routing.StateUpdater
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CreateIntentViewModel(
    eventBus: EventBus
): ViewModel() {
    val state: MutableStateFlow<CreateIntentState> = MutableStateFlow(CreateIntentState())

    val stateUpdater = StateUpdater(eventBus){
        on<CreateIntent.OnChangeStartLocation> {
            state.update {
                it.copy(
                    routeDetails = it.routeDetails.copy(
                        origin = it.routeDetails.origin.copy(
                            value = startLocation
                        )
                    )
                )
            }
        }
        on<CreateIntent.OnChangeDestination> {
            state.update {
                it.copy(
                    routeDetails = it.routeDetails.copy(
                        origin = it.routeDetails.destination.copy(
                            value = destination
                        )
                    )
                )
            }
        }
        on<CreateIntent.OnChangeDate> {
            state.update {
                it.copy(
                    schedule = it.schedule.copy(
                        date = it.schedule.date.copy(
                            value = date
                        )
                    )
                )
            }
        }
        on<CreateIntent.OnChangeTime> {
            state.update {
                it.copy(
                    schedule = it.schedule.copy(
                        time = it.schedule.time.copy(
                            value = time
                        )
                    )
                )
            }
        }
        on<CreateIntent.OnToggleFlexibleTiming> {
            state.update {
                it.copy(
                    preferenceState = it.preferenceState.copy(
                        timing = it.preferenceState.timing.copy(
                            isChecked = isChecked
                        )
                    )
                )
            }
        }
        on<CreateIntent.OnChangeMaxGroupSize> {
            state.update {
                it.copy(
                    preferenceState = it.preferenceState.copy(
                        maxGroupSize = maxGroupSize
                    )
                )
            }
        }
        on<CreateIntent.OnClickCreateIntent> {

        //it.processIntent(SnackBar("Not Implemented", null))
        }
    }
}