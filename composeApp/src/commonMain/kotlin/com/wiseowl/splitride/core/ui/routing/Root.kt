package com.wiseowl.splitride.core.ui.routing

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wiseowl.splitride.onboarding.presentation.OnboardingScreen

@Composable
fun Root(
    navController: NavHostController,
    startDestination: Screen = Screen.Home
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Home> { Text("Home Screen") }
        composable<Screen.Settings>{ Text("Settings Screen") }
        composable<Screen.Onboarding>{ OnboardingScreen() }
    }
}