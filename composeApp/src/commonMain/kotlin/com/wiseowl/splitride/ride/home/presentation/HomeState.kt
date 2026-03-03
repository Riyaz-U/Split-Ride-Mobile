package com.wiseowl.splitride.ride.home.presentation

import com.wiseowl.splitride.core.ui.models.TextState
import com.wiseowl.splitride.ride.home.presentation.models.CreateRideCardState
import com.wiseowl.splitride.ride.home.presentation.models.NearbyGroupsSection
import com.wiseowl.splitride.ride.home.presentation.models.RecentCommuteSection
import com.wiseowl.splitride.ride.home.presentation.models.StatusCard

data class HomeState(
    val title: TextState = TextState("Good morning, Alex"),
    val subtitle: TextState = TextState("Ready to split your commute?"),
    val statusCard: StatusCard = StatusCard(),
    val createRideCardState: CreateRideCardState = CreateRideCardState(),
    val groupsNearbyState: NearbyGroupsSection = NearbyGroupsSection(),
    val recentCommutes: RecentCommuteSection = RecentCommuteSection()
)