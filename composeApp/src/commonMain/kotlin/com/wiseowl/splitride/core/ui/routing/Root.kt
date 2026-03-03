package com.wiseowl.splitride.core.ui.routing

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wiseowl.splitride.authentication.presentation.login.LoginScreen
import com.wiseowl.splitride.authentication.presentation.registration.RegistrationScreen
import com.wiseowl.splitride.core.storage.AuthenticationStorage
import com.wiseowl.splitride.core.storage.UserDetailStorage
import com.wiseowl.splitride.onboarding.presentation.OnboardingScreen
import com.wiseowl.splitride.ride.home.presentation.HomeScreen
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
        composable<Screen.Home> { HomeScreen() }
        composable<Screen.Settings>{ Text("Settings Screen") }
        composable<Screen.Onboarding>{ OnboardingScreen() }
        composable<Screen.Login>{ LoginScreen() }
        composable<Screen.Registration>{ RegistrationScreen() }
    }
}

@Composable
fun getStartDestination(): Screen {
    val userDetailStorage = koinInject<UserDetailStorage>()
    val authenticationStorage = koinInject<AuthenticationStorage>()
    val hasCompletedOnboarding = (userDetailStorage.hasOnboardingCompleted() ?: false)
    val isLoggedIn = authenticationStorage.getToken()!=null

    return if(hasCompletedOnboarding){
        if(isLoggedIn) Screen.Home
        else Screen.Login
    } else Screen.Onboarding
}
