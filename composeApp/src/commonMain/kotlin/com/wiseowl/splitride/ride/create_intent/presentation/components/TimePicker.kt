package com.wiseowl.splitride.ride.create_intent.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.isPm
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.LocalStateUpdater
import com.wiseowl.splitride.core.ui.components.GhostButton
import com.wiseowl.splitride.core.ui.components.PrimaryInputField
import com.wiseowl.splitride.core.ui.models.ButtonState
import com.wiseowl.splitride.core.ui.models.InputFieldState
import com.wiseowl.splitride.ride.create_intent.presentation.CreateIntent
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    modifier: Modifier = Modifier,
    state: InputFieldState,
) {

    val stateUpdater = LocalStateUpdater.current
    val now = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())

    val timePickerState = rememberTimePickerState(
        initialHour = now.hour,
        initialMinute = now.minute,
        is24Hour = false,
    )
    val softInputController = LocalSoftwareKeyboardController.current
    val isPickerVisible = remember { mutableStateOf(false) }

    PrimaryInputField(modifier = modifier.onFocusChanged {
        isPickerVisible.value = it.isFocused
    }.focusable(false), state = state)

    if (isPickerVisible.value) {
        Popup(
            onDismissRequest = { isPickerVisible.value = false },
            alignment = Alignment.TopStart
        ) {
            Column(
                Modifier
                    .background(AppColors.SecondaryContainer)
                    .clip(RoundedCornerShape(6.dp))
                    .shadow(elevation = 4.dp)
                    .padding(16.dp)
            ) {
                TimePicker(state = timePickerState)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    GhostButton(button = ButtonState(text = "Cancel")) {
                        isPickerVisible.value = false
                    }
                    GhostButton(button = ButtonState(text = "Confirm selection")) {
                        isPickerVisible.value = false
                        softInputController?.hide()
                        stateUpdater?.processIntent(CreateIntent.OnChangeTime("${timePickerState.hourInput}:${timePickerState.minuteInput} ${if (timePickerState.isPm) "PM" else "AM"}"))
                    }
                }
            }
        }
    }
}