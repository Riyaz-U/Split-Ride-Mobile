package com.wiseowl.splitride.core.ui.routing

import kotlinx.serialization.Serializable

sealed class Screen() {
    @Serializable
    object Home: Screen()

    @Serializable
    object Settings: Screen()

    @Serializable
    object Onboarding: Screen()

    @Serializable
    object Login: Screen()

    @Serializable
    object Registration: Screen()

    @Serializable
    object CreateIntent: Screen()
}