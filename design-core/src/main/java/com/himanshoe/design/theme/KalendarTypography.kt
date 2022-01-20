package com.himanshoe.design.theme
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

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Stable
class KalendarTypography(
    val h1Medium: TextStyle,

    val h2Regular: TextStyle,
    val h2Medium: TextStyle,

    val h3Regular: TextStyle,
    val h3Medium: TextStyle,
    val h3: TextStyle,

    val h44Regular: TextStyle,
    val h4Medium: TextStyle,

    val body1: TextStyle,
    val body1Medium: TextStyle,

    val body2: TextStyle,
    val body2Medium: TextStyle,
)

val Typography = KalendarTypography(
    h1Medium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Thirty,
        lineHeight = TextUnit.ThirtyFour,
        fontFamily = ttCommonsFontFamily
    ),

    h2Regular = TextStyle(
        fontSize = TextUnit.TwentySix,
        lineHeight = TextUnit.Thirty,
        fontFamily = ttCommonsFontFamily
    ),
    h2Medium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.TwentySix,
        lineHeight = TextUnit.Thirty,
        fontFamily = ttCommonsFontFamily
    ),

    h3Regular = TextStyle(
        fontSize = TextUnit.Twenty,
        lineHeight = TextUnit.TwentyFour,
        fontFamily = ttCommonsFontFamily
    ),
    h3Medium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Twenty,
        lineHeight = TextUnit.TwentyFour,
        fontFamily = ttCommonsFontFamily
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = TextUnit.Twenty,
        lineHeight = TextUnit.TwentyFour,
        fontFamily = ttCommonsFontFamily
    ),

    h44Regular = TextStyle(
        fontSize = TextUnit.Eighteen,
        lineHeight = TextUnit.TwentyTwo,
        fontFamily = ttCommonsFontFamily
    ),

    h4Medium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Eighteen,
        lineHeight = TextUnit.TwentyTwo,
        fontFamily = ttCommonsFontFamily
    ),

    body1 = TextStyle(
        fontSize = TextUnit.Sixteen,
        lineHeight = TextUnit.Twenty,
        fontFamily = ttCommonsFontFamily
    ),
    body1Medium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Sixteen,
        lineHeight = TextUnit.Twenty,
        fontFamily = ttCommonsFontFamily
    ),
    body2 = TextStyle(
        fontSize = TextUnit.Fourteen,
        lineHeight = TextUnit.Eighteen,
        fontFamily = ttCommonsFontFamily
    ),
    body2Medium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Fourteen,
        lineHeight = TextUnit.Eighteen,
        fontFamily = ttCommonsFontFamily
    ),
)

internal val LocalKalendarTypography = staticCompositionLocalOf<KalendarTypography> {
    error("No KalendarTypography provided")
}

