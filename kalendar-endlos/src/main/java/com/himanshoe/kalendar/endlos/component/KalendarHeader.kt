package com.himanshoe.kalendar.endlos.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendar.endlos.component.text.KalendarTitle
import com.himanshoe.kalendar.endlos.component.text.config.KalendarTextColor
import com.himanshoe.kalendar.endlos.component.text.config.KalendarTextConfig
import com.himanshoe.kalendar.endlos.component.text.config.KalendarTextSize
import kotlinx.datetime.Month
import java.util.Locale

@Composable
internal fun KalendarHeader(
    modifier: Modifier,
    month: Month,
    year: Int,
    textColor: Color,
    textSize: KalendarTextSize = KalendarTextSize.Title

) {
    KalendarTitle(
        modifier = modifier.fillMaxWidth(),
        text = getTitleText(month.name, year),
        kalendarTextConfig = KalendarTextConfig(KalendarTextColor(textColor), textSize)
    )
}

internal fun getTitleText(monthName: String, year: Int): String {
    return monthName.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    } + " " + year
}
