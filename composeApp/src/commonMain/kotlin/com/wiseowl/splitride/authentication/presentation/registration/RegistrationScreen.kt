package com.wiseowl.splitride.authentication.presentation.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.components.Body
import com.wiseowl.splitride.core.ui.components.HeadlineLarge
import com.wiseowl.splitride.core.ui.components.PrimaryButton
import com.wiseowl.splitride.core.ui.components.PrimaryInputField
import com.wiseowl.splitride.core.ui.components.SpanText

@Preview
@Composable
fun RegistrationScreen() {
    val state = viewModel(RegistrationViewModel::class).state.collectAsStateWithLifecycle().value

    Column(
        Modifier
            .fillMaxWidth()
            .background(AppColors.Surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadlineLarge(modifier = Modifier.align(Alignment.Start), state = state.title)
        Spacer(Modifier.height(8.dp))
        Body(modifier = Modifier.align(Alignment.Start), state = state.subtitle)
        Spacer(Modifier.height(36.dp))
        PrimaryInputField(state = state.firstName)
        Spacer(Modifier.height(20.dp))
        PrimaryInputField(state = state.lastName)
        Spacer(Modifier.height(20.dp))
        PrimaryInputField(state = state.email)
        Spacer(Modifier.height(20.dp))
        PrimaryInputField(state = state.password)
        Spacer(Modifier.height(20.dp))
        PrimaryInputField(state = state.confirmPassword)
        Spacer(Modifier.height(32.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = false, onCheckedChange = {

                })
            Spacer(Modifier.height(8.dp))
            SpanText(state = state.termsText)
        }
        Spacer(Modifier.height(40.dp))
        PrimaryButton(modifier = Modifier.fillMaxWidth(), button = state.cta)
        Spacer(Modifier.height(40.dp))
        SpanText(state = state.alreadyHaveAccountText)
    }
}