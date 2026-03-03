package com.wiseowl.splitride.ride.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.components.Body
import com.wiseowl.splitride.core.ui.components.Caption
import com.wiseowl.splitride.core.ui.components.GhostButton
import com.wiseowl.splitride.core.ui.components.HeadlineMedium
import com.wiseowl.splitride.core.ui.components.SpanText
import com.wiseowl.splitride.ride.home.presentation.models.StatusCard

@Composable
fun ActiveStatusCard(
    modifier: Modifier = Modifier,
    state: StatusCard,
) {
    Row(
        modifier.fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(AppColors.SecondaryContainer)
            .border(1.dp, color = AppColors.Primary, shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.clip(CircleShape).size(10.dp).background(AppColors.Primary))
            Spacer(Modifier.width(12.dp))
            Column {
                SpanText(state = state.title)
                Caption(state = state.description)
            }
        }
        GhostButton(button = state.cta)
    }
}