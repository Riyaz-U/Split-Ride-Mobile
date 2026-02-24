package com.wiseowl.splitride.core.ui.routing

import kotlinx.serialization.Serializable

sealed class Screen() {
    @Serializable
    object Home: Screen()

    @Serializable
    object Settings: Screen()
}