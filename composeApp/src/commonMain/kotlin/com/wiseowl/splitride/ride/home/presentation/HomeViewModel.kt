package com.wiseowl.splitride.ride.home.presentation

import androidx.lifecycle.ViewModel
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.StateUpdater
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
    eventBus: EventBus
): ViewModel() {
    val state = MutableStateFlow(HomeState())

    val stateUpdater = StateUpdater(eventBus){
        on<HomeIntent.OnClickRideIntent> {

        }
    }
}