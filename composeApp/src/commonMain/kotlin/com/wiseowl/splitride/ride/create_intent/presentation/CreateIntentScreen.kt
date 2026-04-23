package com.wiseowl.splitride.ride.create_intent.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.LocalStateUpdater
import com.wiseowl.splitride.core.ui.components.PrimaryButton
import com.wiseowl.splitride.ride.create_intent.presentation.components.Preferences
import com.wiseowl.splitride.ride.create_intent.presentation.components.RouteDetailsSection
import com.wiseowl.splitride.ride.create_intent.presentation.components.ScheduleSection
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun CreateIntentScreen(
    viewModel: CreateIntentViewModel = koinViewModel<CreateIntentViewModel>()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    CompositionLocalProvider(
        LocalStateUpdater provides viewModel.stateUpdater
    ){
        Box(Modifier.fillMaxSize().safeDrawingPadding()) {
            Column(Modifier.fillMaxSize().padding(horizontal = 16.dp).scrollable(rememberScrollState(), Orientation.Vertical)) {
                RouteDetailsSection(state = state.routeDetails)
                Spacer(Modifier.height(24.dp))
                ScheduleSection(state = state.schedule)
                Spacer(Modifier.height(24.dp))
                Preferences(state = state.preferenceState)
            }
            PrimaryButton(
                modifier = Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(AppColors.Surface)
                    .padding(16.dp),
                button = state.cta
            )
        }
    }
}