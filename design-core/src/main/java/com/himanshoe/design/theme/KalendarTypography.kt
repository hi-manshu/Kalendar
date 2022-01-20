package com.himanshoe.design.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Stable
class KalendarTypography(
    val text1Demibold: TextStyle,

    val text2: TextStyle,
    val text2Demibold: TextStyle,

    val text3Regular: TextStyle,
    val text3Demibold: TextStyle,
    val text3Bold: TextStyle,

    val text4Regular: TextStyle,
    val text4RegularMoreLineHeight: TextStyle,
    val text4Demibold: TextStyle,

    val text5Regular: TextStyle,
    val text5Demibold: TextStyle,

    val text6Regular: TextStyle,
    val text6Demibold: TextStyle,

    val text7Regular: TextStyle,
    val text7Demibold: TextStyle,

    val button1: TextStyle,
    val button2: TextStyle,

    val link1Regular: TextStyle,
    val link2Demibold: TextStyle,
)

val Typography = KalendarTypography(
    text1Demibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Thirty,
        lineHeight = TextUnit.ThirtyFour,
        fontFamily = ttCommonsFontFamily
    ),

    text2 = TextStyle(
        fontSize = TextUnit.TwentySix,
        lineHeight = TextUnit.Thirty,
        fontFamily = ttCommonsFontFamily
    ),
    text2Demibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.TwentySix,
        lineHeight = TextUnit.Thirty,
        fontFamily = ttCommonsFontFamily
    ),

    text3Regular = TextStyle(
        fontSize = TextUnit.Twenty,
        lineHeight = TextUnit.TwentyFour,
        fontFamily = ttCommonsFontFamily
    ),
    text3Demibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Twenty,
        lineHeight = TextUnit.TwentyFour,
        fontFamily = ttCommonsFontFamily
    ),
    text3Bold = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = TextUnit.Twenty,
        lineHeight = TextUnit.TwentyFour,
        fontFamily = ttCommonsFontFamily
    ),

    text4Regular = TextStyle(
        fontSize = TextUnit.Eighteen,
        lineHeight = TextUnit.TwentyTwo,
        fontFamily = ttCommonsFontFamily
    ),
    text4RegularMoreLineHeight = TextStyle(
        fontSize = TextUnit.Eighteen,
        lineHeight = TextUnit.TwentySix,
        fontFamily = ttCommonsFontFamily
    ),
    text4Demibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Eighteen,
        lineHeight = TextUnit.TwentyTwo,
        fontFamily = ttCommonsFontFamily
    ),

    text5Regular = TextStyle(
        fontSize = TextUnit.Sixteen,
        lineHeight = TextUnit.Twenty,
        fontFamily = ttCommonsFontFamily
    ),
    text5Demibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Sixteen,
        lineHeight = TextUnit.Twenty,
        fontFamily = ttCommonsFontFamily
    ),

    text6Regular = TextStyle(
        fontSize = TextUnit.Fourteen,
        lineHeight = TextUnit.Eighteen,
        fontFamily = ttCommonsFontFamily
    ),
    text6Demibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Fourteen,
        lineHeight = TextUnit.Eighteen,
        fontFamily = ttCommonsFontFamily
    ),

    text7Regular = TextStyle(
        fontSize = TextUnit.Twelve,
        lineHeight = TextUnit.Sixteen,
        fontFamily = ttCommonsFontFamily
    ),
    text7Demibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Twelve,
        lineHeight = TextUnit.Sixteen,
        fontFamily = ttCommonsFontFamily
    ),

    button1 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Sixteen,
        lineHeight = TextUnit.Twenty,
        fontFamily = ttCommonsFontFamily
    ),
    button2 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Sixteen,
        lineHeight = TextUnit.Twenty,
        fontFamily = ttCommonsFontFamily
    ),

    link1Regular = TextStyle(
        fontSize = TextUnit.Sixteen,
        lineHeight = TextUnit.Eighteen,
        fontFamily = ttCommonsFontFamily
    ),
    link2Demibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = TextUnit.Fourteen,
        lineHeight = TextUnit.Eighteen,
        fontFamily = ttCommonsFontFamily
    ),
)

internal val LocalKalendarTypography = staticCompositionLocalOf<KalendarTypography> {
    error("No KalendarTypography provided")
}

