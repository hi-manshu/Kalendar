package com.himanshoe.kalendar.config

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarShape
import com.himanshoe.design.theme.KalendarShape.ButtomCurvedShape

data class KalendarKonfig(
    val backgroundColor: Color? = null,
    val calendarColor: Color? = null,
    val hasRadius: Boolean = true,
    val elevation: Dp = if (hasRadius) Grid.One else 0.dp,
    val shape: Shape = if (hasRadius) KalendarShape.SelectedShape else KalendarShape.DefaultRectangle,
    val bottomCurved: Shape = ButtomCurvedShape
)
