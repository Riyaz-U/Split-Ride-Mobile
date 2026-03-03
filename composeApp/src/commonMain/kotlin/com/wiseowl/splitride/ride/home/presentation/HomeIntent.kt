package com.wiseowl.splitride.ride.home.presentation

import com.wiseowl.splitride.core.ui.routing.Intent

sealed class HomeIntent: Intent() {
    object OnClickRideIntent: HomeIntent()
    object OnClickChangeStatus: HomeIntent()
    object OnClickSeeAllNearbyGroups: HomeIntent()
}