package com.himanshoe.design.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

object KalendarTheme {
    val colors: KalendarColor
        @Composable
        get() = LocalKalendarColors.current

    val typography: KalendarTypography
        @Composable
        get() = LocalKalendarTypography.current
}

@Composable
fun ProvideKalendarTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalKalendarColors provides colorPalette(),
        LocalKalendarTypography provides Typography,
        content = content
    )

}

@Composable
fun KalendarTheme(
    content: @Composable () -> Unit,
) {
    ProvideKalendarTheme {
        MaterialTheme(
            colors = debugColors(),
            content = content
        )
    }
}
