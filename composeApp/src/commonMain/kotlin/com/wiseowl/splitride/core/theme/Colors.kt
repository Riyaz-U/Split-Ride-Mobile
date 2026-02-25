package com.wiseowl.splitride.core.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object AppColors {
    // Brand
    val Primary = Color(22, 121, 230)
    val PrimaryTint = Color(203, 221, 243)
    val Contrast = Color(203, 221, 243)

    // Status
    val Success = Color(83, 186, 129)
    val Error = Color(241, 75, 94)

    // Disabled
    val PrimaryDisabled = Color(162, 201, 245)
    val PrimaryDisabledContent = Color(227, 239, 252)
    val SecondaryContainer = Color(231, 241, 252)
    val SecondaryDisabled = Color(218, 218, 218)
    val SecondaryDisabledContent = Color(148, 163, 184)
    val ErrorDisabled = Color(239, 146, 155)

    // Text
    val TextPrimary = Color(0xFF0F172A)
    val TextSecondary = Color(0xFF334155)
    val TextMuted = Color(0xFF94A3B8)

    // Input
    val InputBackground = Color(0xFFF8FAFC)
    val InputFocusedBorder = Color(203, 224, 248)
    val InputUnfocusedBorder = Color(0xFFCBD5E1)
    val InputDisabledBorder = Color(0xFFE2E8F0)
    val InputErrorBorder = Color(244, 151, 168)
    val InputErrorBackground = Color(253, 241, 242)

    // Neutrals (light)
    val Background = Color(0xFFF8FAFC)
    val Surface = Color(246, 247, 248)
    val SurfaceVariant = Color(0xFFF1F5F9)

    // Neutrals (dark)
    val BackgroundDark = Color(0xFF0B1220)
    val SurfaceDark = Color(0xFF111827)
    val SurfaceVariantDark = Color(0xFF1F2937)

    // Error containers
    val ErrorContainer = Color(0xFFF9DEDC)
    val ErrorDark = Color(0xFFF2B8B5)
    val ErrorContainerDark = Color(0xFF8C1D18)
}

val LightColors = lightColorScheme(
    primary = AppColors.Primary,
    onPrimary = Color.White,
    primaryContainer = AppColors.Primary,
    onPrimaryContainer = Color.White,

    secondary = AppColors.Primary,
    onSecondary = Color.White,
    secondaryContainer = AppColors.SurfaceVariant,
    onSecondaryContainer = AppColors.TextPrimary,

    tertiary = AppColors.Primary,
    onTertiary = Color.White,
    tertiaryContainer = AppColors.SurfaceVariant,
    onTertiaryContainer = AppColors.TextPrimary,

    background = AppColors.Background,
    onBackground = AppColors.TextPrimary,

    surface = AppColors.Surface,
    onSurface = AppColors.TextPrimary,

    surfaceVariant = AppColors.SurfaceVariant,
    onSurfaceVariant = AppColors.TextSecondary,

    outline = AppColors.InputUnfocusedBorder,
    outlineVariant = AppColors.InputDisabledBorder,

    error = AppColors.Error,
    onError = Color.White,
    errorContainer = AppColors.ErrorContainer,
    onErrorContainer = Color(0xFF410E0B),

    scrim = Color(0x66000000),
)

val DarkColors = darkColorScheme(
    primary = AppColors.Primary,
    onPrimary = Color.White,
    primaryContainer = AppColors.Primary,
    onPrimaryContainer = Color.White,

    secondary = AppColors.Primary,
    onSecondary = Color.White,
    secondaryContainer = AppColors.SurfaceVariantDark,
    onSecondaryContainer = Color(0xFFE2E8F0),

    tertiary = AppColors.Primary,
    onTertiary = Color.White,
    tertiaryContainer = AppColors.SurfaceVariantDark,
    onTertiaryContainer = Color(0xFFE2E8F0),

    background = AppColors.BackgroundDark,
    onBackground = Color(0xFFE2E8F0),

    surface = AppColors.SurfaceDark,
    onSurface = Color(0xFFE2E8F0),

    surfaceVariant = AppColors.SurfaceVariantDark,
    onSurfaceVariant = Color(0xFFCBD5E1),

    outline = Color(0xFF475569),
    outlineVariant = Color(0xFF334155),

    error = AppColors.ErrorDark,
    onError = Color(0xFF601410),
    errorContainer = AppColors.ErrorContainerDark,
    onErrorContainer = Color(0xFFF9DEDC),

    scrim = Color(0x99000000),
)
