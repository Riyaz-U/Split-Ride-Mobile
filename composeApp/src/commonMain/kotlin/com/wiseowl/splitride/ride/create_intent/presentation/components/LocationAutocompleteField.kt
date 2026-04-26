package com.wiseowl.splitride.ride.create_intent.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.theme.interFontFamily
import com.wiseowl.splitride.core.ui.components.PrimaryInputField
import com.wiseowl.splitride.ride.create_intent.domain.model.PlaceSuggestion
import com.wiseowl.splitride.ride.create_intent.presentation.models.LocationFieldState

@Composable
fun LocationAutocompleteField(
    modifier: Modifier = Modifier,
    state: LocationFieldState,
    onValueChange: (String) -> Unit,
    onSuggestionSelected: (PlaceSuggestion) -> Unit
) {
    Column(modifier = modifier) {
        PrimaryInputField(
            state = state.input,
            onValueChange = onValueChange
        )
        if (state.suggestions.isNotEmpty()) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
                color = AppColors.Surface,
                shadowElevation = 4.dp,
                tonalElevation = 0.dp
            ) {
                Column {
                    state.suggestions.forEach { suggestion ->
                        SuggestionItem(
                            suggestion = suggestion,
                            onClick = { onSuggestionSelected(suggestion) }
                        )
                        HorizontalDivider(color = AppColors.InputUnfocusedBorder)
                    }
                }
            }
        }
    }
}

@Composable
private fun SuggestionItem(
    suggestion: PlaceSuggestion,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "●",
            fontSize = 8.sp,
            color = AppColors.TextMuted
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = suggestion.displayName,
                fontFamily = interFontFamily(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = AppColors.TextPrimary
            )
            if (suggestion.secondaryText.isNotEmpty()) {
                Text(
                    text = suggestion.secondaryText,
                    fontFamily = interFontFamily(),
                    fontSize = 12.sp,
                    color = AppColors.TextMuted
                )
            }
        }
    }
}
