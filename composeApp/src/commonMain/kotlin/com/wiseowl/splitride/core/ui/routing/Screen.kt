package com.wiseowl.splitride.core.ui.routing

sealed class Screen(val id: String) {
    object Home: Screen("home")
    object Settings: Screen("settings")
}