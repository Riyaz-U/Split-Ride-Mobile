package com.wiseowl.splitride.core.ui.routing

sealed class Screen() {
    object Home: Screen()
    object Settings: Screen()
}