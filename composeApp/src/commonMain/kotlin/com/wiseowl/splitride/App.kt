package com.wiseowl.splitride

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wiseowl.splitride.core.storage.UserDetailStorage
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.theme.LightColors
import com.wiseowl.splitride.core.ui.routing.CompletedOnboarding
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.Navigation
import com.wiseowl.splitride.core.ui.routing.Root
import com.wiseowl.splitride.core.ui.routing.Screen
import com.wiseowl.splitride.core.ui.routing.SnackBar
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme(
        colorScheme = LightColors
    ) {
        val snackBarHostState = remember { SnackbarHostState() }
        Scaffold(
            snackbarHost = {
                SnackbarHost(snackBarHostState){
                    Snackbar(
                        it,
                        containerColor = AppColors.SecondaryContainer
                    )
                }
            }
        ) {
            val navController = rememberNavController()
            EventListener(navController, snackBarHostState)
            Root(navController)
        }
    }
}

@Composable
fun EventListener(
    navHostController: NavHostController,
    snackBarHostState: SnackbarHostState,
) {
    val eventBus = koinInject<EventBus>()
    val userDetailStorage = koinInject<UserDetailStorage>()

    LaunchedEffect(eventBus) {
        val channel = eventBus.subscribe()
        for (event in channel) {
            when (event) {
                is Navigation -> navHostController.navigate(event.screen)
                is CompletedOnboarding -> {
                    userDetailStorage.markOnboardingCompleted()
                    navHostController.navigate(Screen.Home)
                }
                is SnackBar -> {
                    snackBarHostState.currentSnackbarData?.dismiss()
                    val result = snackBarHostState.showSnackbar(message = event.text, actionLabel = event.actionLabel)
                    when(result){
                        SnackbarResult.Dismissed -> Unit
                        SnackbarResult.ActionPerformed -> event.action?.let { event.stateUpdater?.processIntent(it) }
                    }
                }
            }
        }
    }
}