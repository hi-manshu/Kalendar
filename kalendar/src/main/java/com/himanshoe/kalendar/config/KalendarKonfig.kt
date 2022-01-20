package com.himanshoe.kalendar.config

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarShape.ButtomCurvedShape

/**
 * [KalendarKonfig] represents the config needed for rendering calendar
 * @param[yearRange] gives the min/max year range
 * @param [backgroundColor] represents the color in background of calendar
 * @param [calendarColor] represents the color of the view
 * @param [hasRadius] gives the radius check for monthView
 * @param [elevation] provides info for Elevation for Firey
 * @param [shape] provides shape for views
 */
data class KalendarKonfig(
    val yearRange: YearRange = YearRange(),
    val backgroundColor: Color? = null,
    val calendarColor: Color? = null,
    val hasRadius: Boolean = true,
    val elevation: Dp = if (hasRadius) Grid.One else 0.dp,
    val shape: Shape = ButtomCurvedShape,
)

/**
 * [YearRange] represents range from
 * [min] years to
 * [max] years
 */
data class YearRange(val min: Int = 0, val max: Int = 0)
