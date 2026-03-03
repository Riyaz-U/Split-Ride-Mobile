package com.wiseowl.splitride.ride.home.presentation.models

import androidx.compose.runtime.Immutable
import com.wiseowl.splitride.core.ui.models.ButtonState
import com.wiseowl.splitride.core.ui.models.TextState
import com.wiseowl.splitride.ride.home.domain.models.RideGroup

@Immutable
data class NearbyGroupsSection(
    val title: TextState = TextState("Groups Near You"),
    val seeAll: ButtonState = ButtonState("See All"),
    val groups: List<RideGroupState> = emptyList()
)

data class RideGroupState(
    val title: TextState,
    val note: TextState,
    val discountString: String,
    val startsInText: TextState
)

fun RideGroup.toRideGroupState(): RideGroupState {
    return RideGroupState(
        title = TextState("$origin -> $destination"),
        note = TextState("Shared with $companion"),
        discountString = "Save $savingInPercent%",
        startsInText = TextState("Starts in $startsIn")
    )
}
