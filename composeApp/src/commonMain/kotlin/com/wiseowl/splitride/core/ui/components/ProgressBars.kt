package com.wiseowl.splitride.core.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.models.ProgressBarState
import com.wiseowl.splitride.core.ui.models.TextState

// 1. Linear Progress Bar — determinate with animated fill
@Composable
fun LinearProgressBar(
    modifier: Modifier = Modifier,
    state: ProgressBarState,
    trackColor: Color = AppColors.PrimaryTint,
    progressColor: Color = AppColors.Primary,
    height: Dp = 8.dp
) {
    val animatedProgress by animateFloatAsState(
        targetValue = state.progress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 400)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(12.dp))
            .background(trackColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(animatedProgress)
                .fillMaxHeight()
                .clip(RoundedCornerShape(12.dp))
                .background(progressColor)
        )
    }
}

// 2. Success Progress Bar — green fill
@Composable
fun SuccessProgressBar(
    modifier: Modifier = Modifier,
    state: ProgressBarState,
    height: Dp = 8.dp
) {
    LinearProgressBar(
        modifier = modifier,
        state = state,
        trackColor = AppColors.Success.copy(alpha = 0.2f),
        progressColor = AppColors.Success,
        height = height
    )
}

// 3. Error Progress Bar — red fill
@Composable
fun ErrorProgressBar(
    modifier: Modifier = Modifier,
    state: ProgressBarState,
    height: Dp = 8.dp
) {
    LinearProgressBar(
        modifier = modifier,
        state = state,
        trackColor = AppColors.Error.copy(alpha = 0.2f),
        progressColor = AppColors.Error,
        height = height
    )
}

// 4. Indeterminate Progress Bar — sliding indicator
@Composable
fun IndeterminateProgressBar(
    modifier: Modifier = Modifier,
    trackColor: Color = AppColors.PrimaryTint,
    progressColor: Color = AppColors.Primary,
    height: Dp = 8.dp
) {
    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = -0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(12.dp))
            .clipToBounds()
            .background(trackColor)
    ) {
        val parentWidthPx = with(LocalDensity.current) { maxWidth.toPx() }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(12.dp))
                .graphicsLayer {
                    translationX = offset * parentWidthPx
                }
                .background(progressColor)
        )
    }
}

// 5. Step Progress Bar — segmented steps (e.g. onboarding)
@Composable
fun StepProgressBar(
    modifier: Modifier = Modifier,
    state: ProgressBarState,
    activeColor: Color = AppColors.Primary,
    inactiveColor: Color = AppColors.PrimaryTint,
    height: Dp = 6.dp,
    spacing: Dp = 6.dp
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        for (step in 0 until state.totalSteps) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(height)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (step < state.currentStep) activeColor else inactiveColor)
            )
        }
    }
}

// region Previews

@Preview
@Composable
private fun ProgressBarsPreview() {
    Column(
        modifier = Modifier.background(AppColors.Surface).padding(16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Caption(state = TextState(text = "Linear — 60%", color = AppColors.TextSecondary))
        LinearProgressBar(state = ProgressBarState(progress = 0.6f))

        Caption(state = TextState(text = "Success — 80%", color = AppColors.TextSecondary))
        SuccessProgressBar(state = ProgressBarState(progress = 0.8f))

        Caption(state = TextState(text = "Error — 30%", color = AppColors.TextSecondary))
        ErrorProgressBar(state = ProgressBarState(progress = 0.3f))

        Caption(state = TextState(text = "Indeterminate", color = AppColors.TextSecondary))
        IndeterminateProgressBar()

        Caption(state = TextState(text = "Step — 2 of 4", color = AppColors.TextSecondary))
        StepProgressBar(state = ProgressBarState(totalSteps = 4, currentStep = 2))
    }
}

// endregion

