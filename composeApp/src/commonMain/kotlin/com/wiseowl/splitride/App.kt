package com.wiseowl.splitride

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.wiseowl.splitride.core.theme.LightColors
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wiseowl.splitride.core.ui.routing.EventBus
import com.wiseowl.splitride.core.ui.routing.Navigation
import com.wiseowl.splitride.core.ui.routing.Root
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme(
        colorScheme = LightColors
    ) {
        Scaffold {
            val navController = rememberNavController()
            EventListener(navController)
            Root(navController)
        }
    }
}

@Composable
fun EventListener(
    navHostController: NavHostController
) {
    val eventBus: EventBus = koinInject()

    LaunchedEffect(eventBus) {
        val channel = eventBus.subscribe()
        for (event in channel) {
            when(event){
               is Navigation -> navHostController.navigate(event.screen)
            }
        }
    }
}