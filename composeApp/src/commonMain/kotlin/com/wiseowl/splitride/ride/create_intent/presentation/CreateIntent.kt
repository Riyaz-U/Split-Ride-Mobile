package com.wiseowl.splitride.ride.create_intent.presentation

import com.wiseowl.splitride.core.ui.routing.Intent

sealed class CreateIntent: Intent(){
    data class OnChangeStartLocation(val startLocation: String): CreateIntent()
    data class OnChangeDestination(val destination: String): CreateIntent()
    data class OnChangeDate(val date: String): CreateIntent()
    data class OnChangeTime(val time: String): CreateIntent()
    data class OnToggleFlexibleTiming(val isChecked: Boolean): CreateIntent()
    data class OnChangeMaxGroupSize(val maxGroupSize: Int): CreateIntent()
    object OnClickCreateIntent: CreateIntent()
}
