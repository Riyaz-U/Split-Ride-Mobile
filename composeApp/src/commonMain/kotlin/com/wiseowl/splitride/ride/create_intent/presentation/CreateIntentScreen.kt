package com.wiseowl.splitride.ride.create_intent.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wiseowl.splitride.core.ui.LocalStateUpdater
import com.wiseowl.splitride.ride.create_intent.presentation.components.RouteDetailsSection
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CreateIntentScreen(
    viewModel: CreateIntentViewModel = koinViewModel<CreateIntentViewModel>()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    CompositionLocalProvider(
        LocalStateUpdater provides viewModel.stateUpdater
    ){
        RouteDetailsSection(state = state.routeDetails)
    }
}