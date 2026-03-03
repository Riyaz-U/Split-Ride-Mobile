package com.wiseowl.splitride.ride.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.LocalStateUpdater
import com.wiseowl.splitride.core.ui.components.HeadlineMedium
import com.wiseowl.splitride.core.ui.components.PrimaryButton
import com.wiseowl.splitride.core.ui.components.Subheading
import com.wiseowl.splitride.core.ui.components.Text
import com.wiseowl.splitride.ride.home.presentation.components.ActiveStatusCard
import com.wiseowl.splitride.ride.home.presentation.components.CreateIntentCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    CompositionLocalProvider(
        LocalStateUpdater provides viewModel.stateUpdater
    ){
        Column(Modifier.safeDrawingPadding().padding(horizontal = 16.dp).fillMaxSize().background(AppColors.Surface).scrollable(rememberScrollState(), orientation = Orientation.Vertical)) {
            Box{
                Box(Modifier.fillMaxWidth().height(200.dp))
                Column {
                    Text(
                        state = state.title,
                        modifier = Modifier.fillMaxWidth(),
                        size = 24.sp,
                        weight = FontWeight.Bold
                    )
                    Text(
                        state = state.subtitle,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(10.dp))
                    CreateIntentCard(Modifier.fillMaxWidth(), state = state.createRideCardState)
                }
            }
            Spacer(Modifier.height(24.dp))
            ActiveStatusCard(modifier = Modifier.fillMaxWidth(), state = state.statusCard)
        }
    }
}