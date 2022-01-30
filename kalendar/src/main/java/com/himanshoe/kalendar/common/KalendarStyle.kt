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

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.himanshoe.kalendar.common.theme.Grid
import com.himanshoe.kalendar.common.theme.KalendarShape

/** [KalendarStyle]sets the style for the calendar
 * @param [kalendarBackgroundColor] represents the color in background of calendar
 * @param [kalendarColor] represents the color of the view
 * @param [kalendarSelector] represents the design for selector
 * @param [hasRadius] gives the radius check for monthView
 * @param [elevation] provides info for Elevation for Firey
 * @param [shape] provides shape for views
 */

data class KalendarStyle(
    val kalendarBackgroundColor: Color? = null,
    val kalendarColor: Color? = null,
    val kalendarSelector: KalendarSelector = KalendarSelector.DiamondShape(),
    val hasRadius: Boolean = true,
    val elevation: Dp = if (hasRadius) Grid.One else Grid.Zero,
    val shape: Shape = KalendarShape.ButtomCurvedShape,
)
