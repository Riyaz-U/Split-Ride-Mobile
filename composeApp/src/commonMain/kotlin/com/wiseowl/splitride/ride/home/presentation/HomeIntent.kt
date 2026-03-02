package com.wiseowl.splitride.ride.home.presentation

import com.wiseowl.splitride.core.ui.routing.Intent

sealed class HomeIntent: Intent() {
    class OnClickRideIntent(): HomeIntent()
}