package com.himanshoe.kalendar.endlos.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.endlos.color.KalendarColors
import com.himanshoe.kalendar.endlos.component.text.KalendarTitle
import com.himanshoe.kalendar.endlos.component.text.config.KalendarTextColor
import com.himanshoe.kalendar.endlos.component.text.config.KalendarTextConfig
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import java.util.Locale

@Composable
internal fun KalendarHeader(
    modifier: Modifier,
    date: LocalDate,
    kalendarTextConfig: KalendarTextConfig = KalendarTextConfig(
        kalendarTextColor = getTextColor(
            date.month
        )
    )

) {
    KalendarTitle(
        modifier = modifier.fillMaxWidth(),
        text = getTitleText(date.month.name, date.year),
        kalendarTextConfig = kalendarTextConfig
    )
}

fun getTextColor(month: Month) = KalendarTextColor(KalendarColors.headerColors[month.value.minus(1)])

internal fun getTitleText(monthName: String, year: Int): String {
    return monthName.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    } + " " + year
}
