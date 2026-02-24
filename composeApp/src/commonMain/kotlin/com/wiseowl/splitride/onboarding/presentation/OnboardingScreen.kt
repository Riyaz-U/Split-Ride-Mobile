package com.wiseowl.splitride.onboarding.presentation

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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.Navigation
import com.wiseowl.splitride.core.ui.routing.Screen
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
        onSkip = { scope.launch { eventBus.push(Navigation(Screen.Home)) } },
        onForward = {
            if (state.currentPage < state.pages.size - 1) state = state.copy(
                currentPage = state.currentPage + 1
            ) else scope.launch { eventBus.push(Navigation(Screen.Home)) }
        }
    )
}

@Composable
fun OnboardingContent(
    state: OnboardingState,
    onSkip: () -> Unit,
    onForward: () -> Unit,
) {
    val pageState = rememberPagerState(initialPage = state.currentPage) { state.pages.size }
    LaunchedEffect(state.currentPage) {
        pageState.animateScrollToPage(state.currentPage)
    }
    Box(
        Modifier.fillMaxSize().background(Color(229, 231, 235)).padding(16.dp)
    ){
        Text(
            "Skip",
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
                .clickable { onSkip() })
        HorizontalPager(
            state = pageState
        ) { pageNo ->
            val page = state.pages[pageNo]
            Box {
                Column(
                    Modifier.fillMaxSize().align(Alignment.Center),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(page.imageRes),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(0.8f).aspectRatio(1f)
                    )
                    Spacer(Modifier.height(32.dp))
                    Text(
                        page.title,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(19, 91, 236)
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        page.description,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )
                    Spacer(Modifier.height(32.dp))
                    Button(
                        onClick = onForward
                    ) {
                        Text(if (pageNo == state.pages.size - 1) "Get Started" else "Next")
                    }
                }
            }
        }
    }
}