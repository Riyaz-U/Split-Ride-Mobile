package com.wiseowl.splitride.ride.create_intent.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiseowl.splitride.core.ui.components.Text
import com.wiseowl.splitride.ride.create_intent.presentation.models.ScheduleState

@Composable
fun ScheduleSection(
    modifier: Modifier = Modifier,
    state: ScheduleState?
) {
    if(state==null) return
    Column(modifier) {
        Text(state = state.title, size = 18.sp, weight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        Row {
            TimePicker(modifier = Modifier.weight(1f), state = state.time)
            Spacer(Modifier.width(16.dp))
            DatePickerDocked(modifier = Modifier.weight(1f), state = state.date)
        }
    }
}