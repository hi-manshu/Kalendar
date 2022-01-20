package com.himanshoe.kalendar.common

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarShape

/** [KalendarStyle]sets the style for the calendar
 * @param [kalendarBackgroundColor] represents the color in background of calendar
 * @param [kalendarColor] represents the color of the view

 * @param [hasRadius] gives the radius check for monthView
 * @param [elevation] provides info for Elevation for Firey
 * @param [shape] provides shape for views
 */

data class KalendarStyle(
    val kalendarBackgroundColor: Color? = null,
    val kalendarColor: Color? = null,
    val kalendarSelector: KalendarSelector = KalendarSelector.Dot(),
    val hasRadius: Boolean = true,
    val elevation: Dp = if (hasRadius) Grid.One else Grid.Zero,
    val shape: Shape = KalendarShape.ButtomCurvedShape,
)
