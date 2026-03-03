package com.wiseowl.splitride.ride.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiseowl.splitride.core.ui.components.PrimaryButton
import com.wiseowl.splitride.core.ui.components.Text
import com.wiseowl.splitride.ride.home.presentation.models.CreateRideCardState

@Composable
fun CreateIntentCard(
    modifier: Modifier = Modifier,
    state: CreateRideCardState,
) {
    Box(
        modifier
            .fillMaxWidth()
            .shadow(10.dp, RoundedCornerShape(12.dp))
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp)

    ) {
        Row {
            Column(Modifier.weight(1f)) {
                Text(
                    state = state.title,
                    modifier = Modifier.fillMaxWidth(),
                    size = 18.sp,
                    weight = FontWeight.Bold
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    state = state.description,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            PrimaryButton(modifier = Modifier.align(Alignment.Bottom), button = state.cta)
        }
    }
}