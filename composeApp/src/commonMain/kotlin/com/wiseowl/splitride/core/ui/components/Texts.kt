package com.wiseowl.splitride.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiseowl.splitride.core.theme.AppColors
import com.wiseowl.splitride.core.theme.interFontFamily
import com.wiseowl.splitride.core.ui.LocalStateUpdater
import com.wiseowl.splitride.core.ui.models.SpanTextState
import com.wiseowl.splitride.core.ui.models.TextSpan
import com.wiseowl.splitride.core.ui.models.TextState
import com.wiseowl.splitride.core.ui.routing.Navigation
import com.wiseowl.splitride.core.ui.routing.Screen

// 1. Headline Large — 36sp Bold
@Composable
fun HeadlineLarge(
    modifier: Modifier = Modifier,
    state: TextState
) {
    Text(
        text = state.text,
        modifier = modifier,
        fontFamily = interFontFamily(),
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        color = state.color
    )
}

// 2. Headline Medium — 24sp Bold
@Composable
fun HeadlineMedium(
    modifier: Modifier = Modifier,
    state: TextState
) {
    Text(
        text = state.text,
        modifier = modifier,
        fontFamily = interFontFamily(),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = state.color
    )
}

// 3. Subheading — 18sp SemiBold
@Composable
fun Subheading(
    modifier: Modifier = Modifier,
    state: TextState
) {
    Text(
        text = state.text,
        modifier = modifier,
        fontFamily = interFontFamily(),
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = state.color
    )
}

// 4. Body — 16sp Regular
@Composable
fun Body(
    modifier: Modifier = Modifier,
    state: TextState
) {
    Text(
        text = state.text,
        modifier = modifier,
        fontFamily = interFontFamily(),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = state.color
    )
}

// 5. Caption — 14sp Regular
@Composable
fun Caption(
    modifier: Modifier = Modifier,
    state: TextState
) {
    Text(
        text = state.text,
        modifier = modifier,
        fontFamily = interFontFamily(),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = state.color
    )
}

// 5. Caption — 14sp Regular
@Composable
fun SpanText(
    modifier: Modifier = Modifier,
    state: SpanTextState
) {
    val stateUpdater = LocalStateUpdater.current
    Text(
        text = buildAnnotatedString {
            var spanStart = 0
            state.spans.forEach { span ->
                append(span.text)
                val spanEnd = spanStart + span.text.length

                val spanStyle = SpanStyle(
                    color = span.color,
                    fontSize = span.size,
                    fontWeight = span.weight,
                    textDecoration = if (span.underline) TextDecoration.Underline else TextDecoration.None
                )

                if (span.intent != null) {
                    addLink(
                        clickable = LinkAnnotation.Clickable(
                            tag = span.intent.toString(),
                            styles = TextLinkStyles(
                                style = spanStyle,
                                pressedStyle = spanStyle,
                                hoveredStyle = spanStyle,
                                focusedStyle = spanStyle
                            )
                        ) {
                            stateUpdater.processIntent(span.intent)
                        },
                        start = spanStart,
                        end = spanEnd
                    )
                } else {
                    addStyle(
                        style = spanStyle,
                        start = spanStart,
                        end = spanEnd
                    )
                }

                spanStart += span.text.length
            }
        },
        modifier = modifier,
        fontFamily = interFontFamily(),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = state.defaultColor
    )
}

// region Previews

@Preview
@Composable
private fun TypographyPreview() {
    Column(
        modifier = Modifier.background(Color.White).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HeadlineLarge(state = TextState(text = "Headline Large", color = AppColors.TextPrimary))
        HeadlineMedium(state = TextState(text = "Headline Medium", color = AppColors.TextPrimary))
        Subheading(state = TextState(text = "Subheading", color = AppColors.TextPrimary))
        Body(state = TextState(text = "Body text", color = AppColors.TextSecondary))
        Caption(state = TextState(text = "Caption text", color = AppColors.TextMuted))
        SpanText(
            state = SpanTextState(
                spans = listOf(
                    TextSpan(
                        text = "This ",
                        color = Color.Red,
                        size = 20.sp,
                        weight = FontWeight.Bold
                    ),
                    TextSpan(
                        text = "is ",
                        color = Color.Green,
                        size = 20.sp,
                        weight = FontWeight.Bold
                    ),
                    TextSpan(
                        text = "Spannable ",
                        color = Color.Blue,
                        size = 20.sp,
                        weight = FontWeight.Bold
                    ),
                    TextSpan(
                        text = "Click here",
                        color = AppColors.Primary,
                        weight = FontWeight.Bold,
                        underline = false,
                        intent = Navigation(Screen.Home)
                    ),
                    TextSpan(
                        text = " for more info.",
                        color = AppColors.TextSecondary
                    )
                ),
                defaultColor = AppColors.TextMuted
            )
        )
    }
}

// endregion
