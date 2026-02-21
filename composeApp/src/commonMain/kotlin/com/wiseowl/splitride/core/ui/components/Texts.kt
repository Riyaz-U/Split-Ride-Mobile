package com.wiseowl.splitride.core.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.wiseowl.splitride.core.theme.interFontFamily

@Composable
fun Text(
    state: String,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(text = state, fontFamily = interFontFamily(), fontWeight = fontWeight, fontSize = fontSize)
}

@Composable
fun Heading(
    state: String
) {
    Text(text = state, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
}

@Composable
fun Title(
    state: String
) {
    Text(text = state, fontSize = 24.sp, fontWeight = FontWeight.Bold)
}