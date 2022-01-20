package com.himanshoe.kalendar.common

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarTheme

sealed class KalendarSelector(
    open val shape: Shape,
    open val selectedColor: Color,
    open val defaultColor: Color,
    open val todayColor: Color,
    open val selectedTextColor: Color,
    open val defaultTextColor: Color,
) {

    data class Circle(
        override val selectedColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultColor: Color = KalendarTheme.colors.white,
        override val todayColor: Color = KalendarTheme.colors.todayColor,
        override val selectedTextColor: Color = KalendarTheme.colors.white,
        override val defaultTextColor: Color = KalendarTheme.colors.black,
    ) : KalendarSelector(CircleShape,
        selectedColor,
        defaultColor,
        todayColor,
        selectedTextColor,
        defaultTextColor)

    data class Dot(
        override val selectedColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultColor: Color = KalendarTheme.colors.white,
        override val todayColor: Color = KalendarTheme.colors.todayColor,
        override val selectedTextColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultTextColor: Color = KalendarTheme.colors.black,
    ) : KalendarSelector(CircleShape,
        selectedColor,
        defaultColor,
        todayColor,
        selectedTextColor,
        defaultTextColor)

    data class Square(
        override val selectedColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultColor: Color = KalendarTheme.colors.white,
        override val todayColor: Color = KalendarTheme.colors.todayColor,
        override val selectedTextColor: Color = KalendarTheme.colors.white,
        override val defaultTextColor: Color = KalendarTheme.colors.black,
    ) : KalendarSelector(RectangleShape,
        selectedColor,
        defaultColor,
        todayColor,
        selectedTextColor,
        defaultTextColor)

    data class Rounded(
        override val selectedColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultColor: Color = KalendarTheme.colors.white,
        override val todayColor: Color = KalendarTheme.colors.todayColor,
        override val selectedTextColor: Color = KalendarTheme.colors.white,
        override val defaultTextColor: Color = KalendarTheme.colors.black,
    ) : KalendarSelector(RoundedCornerShape(Grid.OneHalf),
        selectedColor,
        defaultColor,
        todayColor,
        selectedTextColor,
        defaultTextColor)
}
