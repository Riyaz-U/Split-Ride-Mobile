package com.wiseowl.splitride.ride.home.presentation.models

import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.models.ButtonState
import com.wiseowl.splitride.core.ui.models.SpanTextState
import com.wiseowl.splitride.core.ui.models.TextSpan
import com.wiseowl.splitride.core.ui.models.TextState
import com.wiseowl.splitride.ride.home.presentation.HomeIntent

data class StatusCard(
    val title: SpanTextState = SpanTextState(spans = emptyList()),
    val description: TextState = TextState("Visible to riders near Downtown", AppColors.Primary),
    val cta: ButtonState = ButtonState(
        text =  "Change",
        intent = HomeIntent.OnClickChangeStatus
    )
){
    constructor(
        isActive: Boolean
    ): this(
        title = SpanTextState(
            spans = listOf(
                TextSpan("Your Status: ", AppColors.TextSecondary),
                TextSpan(if(isActive) "Active" else "Inactive", if(isActive) AppColors.Primary else AppColors.Error)
            )
        )
    )
}
