package com.wiseowl.splitride.core.ui.routing

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wiseowl.splitride.core.storage.UserDetailStorage
import com.wiseowl.splitride.onboarding.presentation.OnboardingScreen
import org.koin.compose.koinInject

@Composable
fun Root(
    navController: NavHostController,
){
    val startDestination: Screen = getStartDestination()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Home> { Text("Home Screen") }
        composable<Screen.Settings>{ Text("Settings Screen") }
        composable<Screen.Onboarding>{ OnboardingScreen() }
        composable<Screen.Registration>{ Text("Registration") }
    }
}

@Composable
fun getStartDestination(): Screen {
    val userDetailStorage = koinInject<UserDetailStorage>()
    val hasCompletedOnboarding = userDetailStorage.hasOnboardingCompleted() ?: false

    return if(hasCompletedOnboarding) Screen.Home
    else Screen.Onboarding
}
