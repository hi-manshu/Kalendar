package com.himanshoe.design.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color

data class KalendarColor(
    val selectedColor: Color,
    val todayColor: Color,
    val background: Color,
    val transparent: Color,
    val white: Color,
    val black: Color,
)

internal fun colorPalette() = KalendarColor(
    selectedColor = Fire50,
    todayColor = Fire15,
    background = Color.White,
    transparent = Color.Transparent,
    white = Color.White,
    black = Color.Black
)

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [KalendarTheme.colors].
 */
fun debugColors(
    darkTheme: Boolean = false,
    debugColor: Color = Color.Magenta,
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)

