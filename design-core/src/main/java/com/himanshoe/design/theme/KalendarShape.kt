package com.himanshoe.design.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

object KalendarShape {
    @Stable
    val SelectedShape: Shape = RoundedCornerShape(Grid.Two)

    @Stable
    val DefaultRectangle: Shape = RectangleShape

    @Stable
    val ButtomCurvedShape: Shape = RoundedCornerShape(bottomEnd = Grid.Two, bottomStart = Grid.Two)

    @Stable
    val CircularShape: Shape = CircleShape
}
