package com.himanshoe.kalendar.common.ui
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

import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.theme.Grid

@Composable
internal fun KalendarDot(
    isSelected: Boolean,
    isToday: Boolean,
    kalendarSelector: KalendarSelector,
) {
    Surface(
        shape = kalendarSelector.shape,
        color = getColor(isSelected, isToday, kalendarSelector),
        modifier = Modifier
            .size(Grid.One),
        content = {}
    )
}

private fun getColor(
    isSelected: Boolean,
    isToday: Boolean,
    kalendarSelector: KalendarSelector,
): Color {
    return when {
        isToday -> kalendarSelector.todayColor
        isSelected -> kalendarSelector.selectedColor
        else -> kalendarSelector.defaultColor
    }
}
