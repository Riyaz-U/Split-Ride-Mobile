package com.wiseowl.splitride.core.ui.components

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.wiseowl.splitride.core.ui.models.ButtonState

@Preview
@Composable
fun PrimaryButton(
    button: ButtonState = ButtonState(text = "BUTTON")
){
    Button(
        onClick = {}
    ){
        Text(state = button.text, fontSize = 14.sp)
    }
}