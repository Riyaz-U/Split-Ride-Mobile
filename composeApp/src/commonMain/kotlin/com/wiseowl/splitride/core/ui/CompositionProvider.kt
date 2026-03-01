package com.wiseowl.splitride.core.ui

import androidx.compose.runtime.compositionLocalOf
import com.wiseowl.splitride.core.ui.routing.StateUpdater

val LocalStateUpdater = compositionLocalOf<StateUpdater?> { error("No StateUpdater provided") }