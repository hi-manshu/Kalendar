package com.himanshoe.kalendar.common
/*
* MIT License
*
* Copyright (c) 2022 Himanshu Singh
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.himanshoe.kalendar.common.theme.Grid
import com.himanshoe.kalendar.common.theme.KalendarTheme

sealed class KalendarSelector(
    open val shape: Shape,
    open val selectedColor: Color,
    open val defaultColor: Color,
    open val todayColor: Color,
    open val selectedTextColor: Color,
    open val defaultTextColor: Color,
    open val eventTextColor: Color,
) {

    data class Circle(
        override val selectedColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultColor: Color = KalendarTheme.colors.white,
        override val eventTextColor: Color = KalendarTheme.colors.eventTextColor,
        override val todayColor: Color = KalendarTheme.colors.todayColor,
        override val selectedTextColor: Color = KalendarTheme.colors.white,
        override val defaultTextColor: Color = KalendarTheme.colors.black,
    ) : KalendarSelector(
        CircleShape,
        selectedColor,
        defaultColor,
        todayColor,
        selectedTextColor,
        defaultTextColor,
        eventTextColor
    )

    data class Dot(
        override val selectedColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultColor: Color = KalendarTheme.colors.white,
        override val eventTextColor: Color = KalendarTheme.colors.eventTextColor,
        override val todayColor: Color = KalendarTheme.colors.todayColor,
        override val selectedTextColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultTextColor: Color = KalendarTheme.colors.black,
    ) : KalendarSelector(
        CircleShape,
        selectedColor,
        defaultColor,
        todayColor,
        selectedTextColor,
        defaultTextColor,
        eventTextColor
    )

    data class Square(
        override val selectedColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultColor: Color = KalendarTheme.colors.white,
        override val todayColor: Color = KalendarTheme.colors.todayColor,
        override val eventTextColor: Color = KalendarTheme.colors.eventTextColor,
        override val selectedTextColor: Color = KalendarTheme.colors.white,
        override val defaultTextColor: Color = KalendarTheme.colors.black,
    ) : KalendarSelector(
        RectangleShape,
        selectedColor,
        defaultColor,
        todayColor,
        selectedTextColor,
        defaultTextColor,
        eventTextColor
    )

    data class CutCornerSquare(
        override val selectedColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultColor: Color = KalendarTheme.colors.white,
        override val todayColor: Color = KalendarTheme.colors.todayColor,
        override val eventTextColor: Color = KalendarTheme.colors.eventTextColor,
        override val selectedTextColor: Color = KalendarTheme.colors.white,
        override val defaultTextColor: Color = KalendarTheme.colors.black,
    ) : KalendarSelector(
        CutCornerShape(Grid.OneHalf),
        selectedColor,
        defaultColor,
        todayColor,
        selectedTextColor,
        defaultTextColor,
        eventTextColor
    )

    data class DiamondShape(
        override val selectedColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultColor: Color = KalendarTheme.colors.white,
        override val todayColor: Color = KalendarTheme.colors.todayColor,
        override val eventTextColor: Color = KalendarTheme.colors.eventTextColor,
        override val selectedTextColor: Color = KalendarTheme.colors.white,
        override val defaultTextColor: Color = KalendarTheme.colors.black,
    ) : KalendarSelector(
        CutCornerShape(50),
        selectedColor,
        defaultColor,
        todayColor,
        selectedTextColor,
        defaultTextColor,
        eventTextColor
    )

    data class Rounded(
        override val selectedColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultColor: Color = KalendarTheme.colors.white,
        override val todayColor: Color = KalendarTheme.colors.todayColor,
        override val eventTextColor: Color = KalendarTheme.colors.eventTextColor,
        override val selectedTextColor: Color = KalendarTheme.colors.white,
        override val defaultTextColor: Color = KalendarTheme.colors.black,
    ) : KalendarSelector(
        RoundedCornerShape(Grid.OneHalf),
        selectedColor,
        defaultColor,
        todayColor,
        selectedTextColor,
        defaultTextColor,
        eventTextColor
    )
    data class CustomShape(
        override val shape: Shape,
        override val selectedColor: Color = KalendarTheme.colors.selectedColor,
        override val defaultColor: Color = KalendarTheme.colors.white,
        override val todayColor: Color = KalendarTheme.colors.todayColor,
        override val eventTextColor: Color = KalendarTheme.colors.eventTextColor,
        override val selectedTextColor: Color = KalendarTheme.colors.white,
        override val defaultTextColor: Color = KalendarTheme.colors.black,
    ) : KalendarSelector(
        shape,
        selectedColor,
        defaultColor,
        todayColor,
        selectedTextColor,
        defaultTextColor,
        eventTextColor
    )
}
