package com.wiseowl.splitride.ride.create_intent.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.LocalStateUpdater
import com.wiseowl.splitride.core.ui.components.Text
import com.wiseowl.splitride.core.ui.models.TextState
import com.wiseowl.splitride.ride.create_intent.presentation.CreateIntent
import com.wiseowl.splitride.ride.create_intent.presentation.models.OptionPreferenceState
import com.wiseowl.splitride.ride.create_intent.presentation.models.TimingPreferenceState

@Composable
fun Preferences(
    modifier: Modifier = Modifier,
    state: OptionPreferenceState,
) {
    Column(modifier) {
        Text(state = state.title, size = 18.sp, weight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        TimingPreference(state = state.timing)
        Spacer(Modifier.height(16.dp))
        GroupSizePreference(state.maxGroupSize)
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun TimingPreference(
    state: TimingPreferenceState,
) {
    val stateUpdater = LocalStateUpdater.current
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
            .background(AppColors.SecondaryContainer).padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(state = state.title, size = 16.sp, weight = FontWeight.SemiBold)
            Text(state = state.subtitle, size = 12.sp)
        }
        Switch(
            checked = state.isChecked,
            colors = SwitchDefaults.colors(
                checkedBorderColor = AppColors.Primary,
                checkedTrackColor = AppColors.Primary
            ),
            onCheckedChange = {
                stateUpdater?.processIntent(CreateIntent.OnToggleFlexibleTiming(it))
            }
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun GroupSizePreference(
    groupSize: Int,
) {
    val stateUpdater = LocalStateUpdater.current
    val options = remember {
        listOf(1, 2, 3, 4)
    }

    Column(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp))
            .background(AppColors.SecondaryContainer).padding(16.dp),
    ) {
        Text(state = TextState("Max Group Size"), size = 16.sp, weight = FontWeight.SemiBold)
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(AppColors.Surface)
                .border(1.dp, AppColors.Contrast, RoundedCornerShape(12.dp)).padding(4.dp)
        ) {
            options.forEach {
                Box(
                    Modifier.weight(1f).clip(RoundedCornerShape(12.dp))
                        .background(if (it == groupSize) AppColors.Primary else Color.Transparent)
                        .clickable {
                            stateUpdater?.processIntent(
                                CreateIntent.OnChangeMaxGroupSize(it)
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 26.dp),
                        state = TextState(it.toString()),
                        size = 14.sp,
                        weight = FontWeight.Medium
                    )
                }
            }
        }
    }
}