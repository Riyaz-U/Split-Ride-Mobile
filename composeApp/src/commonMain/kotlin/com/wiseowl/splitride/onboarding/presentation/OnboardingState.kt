package com.wiseowl.splitride.onboarding.presentation

import com.wiseowl.splitride.core.ui.models.TextState
import com.wiseowl.splitride.onboarding.presentation.models.OnboardingPage
import splitride.composeapp.generated.resources.Res
import splitride.composeapp.generated.resources.onboarding_image_1
import splitride.composeapp.generated.resources.onboarding_image_2
import splitride.composeapp.generated.resources.onboarding_image_3

data class OnboardingState(
    val currentPage: Int = 0,
    val totalPages: Int = 3,
    val pages: List<OnboardingPage> = listOf(
        OnboardingPage(
            title = TextState("Your Route, Divided."),
            description = TextState("Our smart matching algorithm connects\n" +
                    "you with riders traveling your way, turning\n" +
                    "a solo trip into a shared journey."),
            imageRes = Res.drawable.onboarding_image_1
        ),
        OnboardingPage(
            title = TextState("Share the Ride,\n" +
                    "Split the Cost."),
            description = TextState("Connect with travelers heading\n" +
                    "your way. Reduce traffic and save\n" +
                    "money by filling every seat."),
            imageRes = Res.drawable.onboarding_image_2
        ),
        OnboardingPage(
            title = TextState("Seamless Payments"),
            description = TextState("No more awkward cash exchanges.\n" +
                    "Split the bill automatically and\n" +
                    "securely within the app right after\n" +
                    "your ride."),
            imageRes = Res.drawable.onboarding_image_3
        )
    )
)
