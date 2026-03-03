package com.wiseowl.splitride.ride.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.StateUpdater
import com.wiseowl.splitride.ride.home.domain.HomeRepository
import com.wiseowl.splitride.ride.home.presentation.models.NearbyGroupsSection
import com.wiseowl.splitride.ride.home.presentation.models.RecentCommuteSection
import com.wiseowl.splitride.ride.home.presentation.models.StatusCard
import com.wiseowl.splitride.ride.home.presentation.models.toRecentCommuteState
import com.wiseowl.splitride.ride.home.presentation.models.toRideGroupState
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    eventBus: EventBus,
    private val homeRepository: HomeRepository
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        viewModelScope.launch {
            coroutineScope {
                val activeDeferred = async { homeRepository.getActiveStatus() }
                val groupsDeferred = async { homeRepository.getNearbyGroups() }
                val commuteDeferred = async { homeRepository.getRecentCommute() }

                _state.value = HomeState(
                    statusCard = StatusCard(
                        isActive = activeDeferred.await()
                    ),
                    groupsNearbyState = NearbyGroupsSection(
                        groups = groupsDeferred.await()
                            .map { it.toRideGroupState() }
                    ),
                    recentCommutes = RecentCommuteSection(
                        commutes = commuteDeferred.await()
                            .map { it.toRecentCommuteState() }
                    )
                )
            }
        }
    }

    val stateUpdater = StateUpdater(eventBus){
        on<HomeIntent.OnClickRideIntent> {}
        on<HomeIntent.OnClickChangeStatus> {}
        on<HomeIntent.OnClickSeeAllNearbyGroups> {}
    }
}