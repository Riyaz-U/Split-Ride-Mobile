package com.wiseowl.splitride.ride.home.presentation.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.intl.Locale
import com.wiseowl.splitride.core.ui.models.TextState
import com.wiseowl.splitride.ride.home.domain.models.CommuteDetail
import io.ktor.util.date.GMTDate
import io.ktor.util.reflect.instanceOf
import kotlin.time.Instant

@Immutable
data class RecentCommuteSection(
    val title: TextState = TextState("Recent Commutes"),
    val commutes: List<RecentCommuteState> = emptyList()
)

data class RecentCommuteState(
    val title: TextState,
    val description: TextState,
    val price: TextState
)


fun CommuteDetail.toRecentCommuteState(): RecentCommuteState {
    val date = GMTDate(date.toEpochMilliseconds()).dayOfWeek
    return RecentCommuteState(
        title = TextState("$origin -> $destination"),
        description = TextState("Shared with $sharedWith * $date"),
        price = TextState("-$currency$saved"),
    )
}
