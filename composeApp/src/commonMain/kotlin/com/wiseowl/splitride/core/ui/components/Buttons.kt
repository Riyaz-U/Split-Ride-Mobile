package com.wiseowl.splitride.core.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiseowl.splitride.core.ui.models.ButtonState
import com.wiseowl.splitride.core.ui.routing.Intent

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    button: ButtonState = ButtonState(text = "BUTTON"),
    onClick: (() -> Unit)? = null
){
    val elevation = ButtonDefaults.buttonElevation(
        pressedElevation = 0.dp,
        hoveredElevation = 0.dp,
        focusedElevation = 0.dp,
        defaultElevation = 3.dp
    )
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(19, 91, 236)),
        shapes = ButtonDefaults.shapes(shape = RoundedCornerShape(12.dp)),
        elevation = elevation,
        onClick = {
            if(onClick!=null) onClick() else { /** Handle using some reducer holder */ }
        },
    ){
        Text(state = button.text, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
    }
}