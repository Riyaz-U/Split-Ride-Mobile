package com.wiseowl.splitride.onboarding.presentation

import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.components.Body
import com.wiseowl.splitride.core.ui.components.GhostButton
import com.wiseowl.splitride.core.ui.components.HeadlineMedium
import com.wiseowl.splitride.core.ui.components.PrimaryButton
import com.wiseowl.splitride.core.ui.models.ButtonState
import com.wiseowl.splitride.core.ui.routing.CompletedOnboarding
import com.wiseowl.splitride.core.ui.routing.EventBus
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Preview
@Composable
fun OnboardingScreen() {
    var state by remember { mutableStateOf(OnboardingState()) }
    val eventBus = koinInject<EventBus>()
    val scope = rememberCoroutineScope()
    OnboardingContent(
        state = state,
        onSkip = { scope.launch { eventBus.push(CompletedOnboarding) } },
        onForward = {
            if (state.currentPage < state.pages.size - 1) state = state.copy(
                currentPage = state.currentPage + 1
            ) else scope.launch { eventBus.push(CompletedOnboarding) }
        }
    )
}

@Composable
fun OnboardingContent(
    state: OnboardingState,
    onSkip: () -> Unit,
    onForward: () -> Unit,
) {
    Box(
        Modifier.fillMaxSize().background(AppColors.Surface)
    ) {
        GhostButton(
            button = ButtonState("Skip"),
            modifier = Modifier.align(Alignment.TopEnd).safeGesturesPadding().clickable { onSkip() }
        )
        val page by rememberUpdatedState(state.pages[state.currentPage])
        val animation = remember(page) { AnimationState(0f) }
        LaunchedEffect(page){
            animation.animateTo(1f, tween(700))
        }
        Box {
            Column(
                Modifier.fillMaxSize().align(Alignment.Center).alpha(animation.value),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(page.imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(0.8f).aspectRatio(1f)
                )
                Spacer(Modifier.height(32.dp))
                HeadlineMedium(state = page.title)
                Spacer(Modifier.height(16.dp))
                Body(state = page.description)
                Spacer(Modifier.height(32.dp))
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    button = ButtonState(text = if (state.currentPage == state.pages.size - 1) "Get Started" else "Next"),
                    onClick = onForward
                )
            }
        }
    }
}