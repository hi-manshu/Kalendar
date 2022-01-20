package com.himanshoe.design.theme

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

