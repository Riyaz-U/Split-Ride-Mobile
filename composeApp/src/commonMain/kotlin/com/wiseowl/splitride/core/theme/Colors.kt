package com.wiseowl.splitride.core.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object AppColors {
    // Brand
    val Accent = Color(19, 91, 236) // (19, 91, 236)

    // Neutrals (light)
    val Background = Color(0xFFF8FAFC)
    val Surface = Color(0xFFE5E7EB) // (229, 231, 235)
    val SurfaceVariant = Color(0xFFF1F5F9)

    // Neutrals (dark)
    val BackgroundDark = Color(0xFF0B1220)
    val SurfaceDark = Color(0xFF111827)
    val SurfaceVariantDark = Color(0xFF1F2937)

    // Status
    val Error = Color(0xFFB3261E)
    val ErrorContainer = Color(0xFFF9DEDC)
    val ErrorDark = Color(0xFFF2B8B5)
    val ErrorContainerDark = Color(0xFF8C1D18)
}

val LightColors = lightColorScheme(
    primary = AppColors.Accent,
    onPrimary = Color.White,
    primaryContainer = AppColors.Accent,
    onPrimaryContainer = Color.White,

    secondary = AppColors.Accent,
    onSecondary = Color.White,
    secondaryContainer = AppColors.SurfaceVariant,
    onSecondaryContainer = Color(0xFF0F172A),

    tertiary = AppColors.Accent,
    onTertiary = Color.White,
    tertiaryContainer = AppColors.SurfaceVariant,
    onTertiaryContainer = Color(0xFF0F172A),

    background = AppColors.Background,
    onBackground = Color(0xFF0F172A),

    surface = AppColors.Surface,
    onSurface = Color(0xFF0F172A),

    surfaceVariant = AppColors.SurfaceVariant,
    onSurfaceVariant = Color(0xFF334155),

    outline = Color(0xFFCBD5E1),
    outlineVariant = Color(0xFFE2E8F0),

    error = AppColors.Error,
    onError = Color.White,
    errorContainer = AppColors.ErrorContainer,
    onErrorContainer = Color(0xFF410E0B),

    scrim = Color(0x66000000),
)

val DarkColors = darkColorScheme(
    primary = AppColors.Accent,
    onPrimary = Color.White,
    primaryContainer = AppColors.Accent,
    onPrimaryContainer = Color.White,

    secondary = AppColors.Accent,
    onSecondary = Color.White,
    secondaryContainer = AppColors.SurfaceVariantDark,
    onSecondaryContainer = Color(0xFFE2E8F0),

    tertiary = AppColors.Accent,
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
