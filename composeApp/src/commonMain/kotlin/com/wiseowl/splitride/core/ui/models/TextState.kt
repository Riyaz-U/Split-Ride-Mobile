package com.wiseowl.splitride.core.ui.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.wiseowl.splitride.core.ui.routing.Intent

data class TextState(
    val text: String,
    val color: Color = Color.Unspecified
)

data class SpanTextState(
    val spans: List<TextSpan> = emptyList(),
    val defaultColor: Color = Color.Unspecified
)

data class TextSpan(
    val text: String,
    val color: Color = Color.Unspecified,
    val size: TextUnit = TextUnit.Unspecified,
    val weight: FontWeight? = null,
    val underline: Boolean = false,
    val intent: Intent? = null
)
