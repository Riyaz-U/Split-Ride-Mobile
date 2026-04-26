package com.wiseowl.splitride.ride.create_intent.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiseowl.splitride.core.ui.LocalStateUpdater
import com.wiseowl.splitride.core.ui.components.Text
import com.wiseowl.splitride.ride.create_intent.presentation.CreateIntent
import com.wiseowl.splitride.ride.create_intent.presentation.models.RouteDetailsState

@Preview
@Composable
fun RouteDetailsSection(
    modifier: Modifier = Modifier,
    state: RouteDetailsState = RouteDetailsState()
) {
    val stateUpdater = LocalStateUpdater.current
    Column(modifier) {
        Text(state = state.title, size = 18.sp, weight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        LocationAutocompleteField(
            state = state.origin,
            onValueChange = { stateUpdater?.processIntent(CreateIntent.OnChangeStartLocation(it)) },
            onSuggestionSelected = { stateUpdater?.processIntent(CreateIntent.OnSelectOriginSuggestion(it)) }
        )
        Spacer(Modifier.height(16.dp))
        LocationAutocompleteField(
            state = state.destination,
            onValueChange = { stateUpdater?.processIntent(CreateIntent.OnChangeDestination(it)) },
            onSuggestionSelected = { stateUpdater?.processIntent(CreateIntent.OnSelectDestinationSuggestion(it)) }
        )
    }
}
