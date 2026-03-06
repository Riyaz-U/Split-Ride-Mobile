package com.wiseowl.splitride.ride.create_intent.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.LocalStateUpdater
import com.wiseowl.splitride.core.ui.components.PrimaryInputField
import com.wiseowl.splitride.core.ui.models.InputFieldState
import com.wiseowl.splitride.ride.create_intent.presentation.CreateIntent
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

@Composable
fun DatePickerDocked(
    modifier: Modifier = Modifier,
    state: InputFieldState
) {
    val stateUpdater = LocalStateUpdater.current
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    datePickerState.selectedDateMillis?.let {
        stateUpdater?.processIntent(CreateIntent.OnChangeDate(convertMillisToDate(it)))
    } ?: ""

    Box(
        modifier = modifier
    ) {
        PrimaryInputField(
            modifier = Modifier.onFocusChanged{
                showDatePicker = it.isFocused
            },
            state = state
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(AppColors.Surface)
                        .padding(16.dp)
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }
}

fun convertMillisToDate(epochMillis: Long): String {
    val instant = Instant.fromEpochMilliseconds(epochMillis)
    val dt = instant.toLocalDateTime(TimeZone.currentSystemDefault())

    return "${dt.dayOfMonth}/${dt.monthNumber}/${dt.year}"
}