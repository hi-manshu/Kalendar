package com.himanshoe.kalendar.common

import androidx.annotation.IntRange
import java.util.Locale

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

/**
 * [KalendarKonfig] represents the config needed for rendering calendar
 * @param[yearRange] gives the min/max year range
 * @param[weekCharacters] helps you set the number of character in Week name, default is 3
 * @param[locale] helps you set the locale where default is [Locale.ENGLISH]
 */
data class KalendarKonfig(
    val yearRange: YearRange = YearRange(),
    @IntRange(from = 1, to = 4)
    val weekCharacters: Int = 3,
    val locale: Locale = Locale.ENGLISH,
)

/**
 * [YearRange] represents range from
 * [min] years to
 * [max] years
 */
data class YearRange(val min: Int = 0, val max: Int = 0)
