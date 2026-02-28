package com.wiseowl.splitride.authentication.presentation.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.ui.LocalStateUpdater
import com.wiseowl.splitride.core.ui.components.Body
import com.wiseowl.splitride.core.ui.components.HeadlineLarge
import com.wiseowl.splitride.core.ui.components.PrimaryButton
import com.wiseowl.splitride.core.ui.components.PrimaryInputField
import com.wiseowl.splitride.core.ui.components.SpanText
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun RegistrationScreen() {
    val viewModel: RegistrationViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val stateUpdater = viewModel.stateUpdater

    CompositionLocalProvider(
        LocalStateUpdater provides stateUpdater
    ){
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .background(AppColors.Surface)
                .padding(horizontal = 16.dp)
                .safeDrawingPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                Column {
                    HeadlineLarge(modifier = Modifier.align(Alignment.Start), state = state.title)
                    Spacer(Modifier.height(8.dp))
                    Body(modifier = Modifier.align(Alignment.Start), state = state.subtitle)
                    Spacer(Modifier.height(36.dp))
                    Row {
                        PrimaryInputField(modifier = Modifier.weight(1f).padding(end = 8.dp), state = state.firstName){
                            stateUpdater.processIntent(RegistrationIntent.OnChangeFirstName(it))
                        }
                        PrimaryInputField(modifier = Modifier.weight(1f).padding(start = 8.dp), state = state.lastName){
                            stateUpdater.processIntent(RegistrationIntent.OnChangeLastName(it))
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    PrimaryInputField(state = state.email){
                        stateUpdater.processIntent(RegistrationIntent.OnChangeEmail(it))
                    }
                    Spacer(Modifier.height(20.dp))
                    PrimaryInputField(state = state.password){
                        stateUpdater.processIntent(RegistrationIntent.OnChangePassword(it))
                    }
                    Spacer(Modifier.height(20.dp))
                    PrimaryInputField(state = state.confirmPassword){
                        stateUpdater.processIntent(RegistrationIntent.OnChangeConfirmedPassword(it))
                    }
                    Spacer(Modifier.height(32.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = state.termsAccepted, onCheckedChange = {
                                stateUpdater.processIntent(RegistrationIntent.OnClickTermsCheckbox(it))
                            })
                        Spacer(Modifier.height(8.dp))
                        SpanText(state = state.termsText)
                    }
                    Spacer(Modifier.height(40.dp))
                    PrimaryButton(modifier = Modifier.fillMaxWidth(), button = state.cta){
                        stateUpdater.processIntent(RegistrationIntent.OnClickRegister)
                    }
                    Spacer(Modifier.height(32.dp))
                    SpanText(modifier = Modifier.align(Alignment.CenterHorizontally), state = state.alreadyHaveAccountText)
                }
            }
        }
    }
}