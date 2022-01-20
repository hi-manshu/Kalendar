package com.himanshoe.design.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.himanshoe.design.R

val ttCommonsFontFamily = FontFamily(
    Font(R.font.tt_commons_light, FontWeight.Light),
    Font(R.font.tt_commons_regular, FontWeight.Normal),
    Font(R.font.tt_commons_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.tt_commons_medium, FontWeight.Medium),
    Font(R.font.tt_commons_Medium, FontWeight.SemiBold),
    Font(R.font.tt_commons_Medium, FontWeight.Bold)
)
