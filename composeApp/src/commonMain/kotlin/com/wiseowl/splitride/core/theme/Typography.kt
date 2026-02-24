package com.wiseowl.splitride.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import splitride.composeapp.generated.resources.Res
import splitride.composeapp.generated.resources.inter_bold
import splitride.composeapp.generated.resources.inter_extra_bold
import splitride.composeapp.generated.resources.inter_medium
import splitride.composeapp.generated.resources.inter_regular

/**
 * App font family backed by commonMain/composeResources/font.
 *
 * Note: org.jetbrains.compose.resources.Font(...) is @Composable, so this must be created in a composable scope.
 */
@Composable
fun interFontFamily(): FontFamily = FontFamily(
    Font(Res.font.inter_regular, weight = FontWeight.Normal),
    Font(Res.font.inter_medium, weight = FontWeight.Medium),
    Font(Res.font.inter_bold, weight = FontWeight.Bold),
    Font(Res.font.inter_extra_bold, weight = FontWeight.ExtraBold),
)