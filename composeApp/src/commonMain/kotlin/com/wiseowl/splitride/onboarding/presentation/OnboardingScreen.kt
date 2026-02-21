package com.wiseowl.splitride.onboarding.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

@Preview
@Composable
fun OnboardingScreen() {
    val state by remember { mutableStateOf(OnboardingState()) }
    OnboardingContent(state = state)
}

@Composable
fun OnboardingContent(
    state: OnboardingState
){
    val pageState = rememberPagerState{ state.pages.size }

    HorizontalPager(
        state = pageState,
    ){pageNo ->
        val page = state.pages[pageNo]
        Column(
            Modifier.fillMaxSize().background(Color(229, 231, 235)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(page.imageRes), contentDescription = null, modifier = Modifier.fillMaxWidth(0.8f).aspectRatio(1f))
            Spacer(Modifier.height(32.dp))
            Text(page.title, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color(19, 91, 236))
            Spacer(Modifier.height(16.dp))
            Text(page.description, fontSize = 16.sp, textAlign = TextAlign.Center, lineHeight = 24.sp)
        }
    }
}