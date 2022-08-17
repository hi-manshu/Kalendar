package com.himanshoe.kalendarkit.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.himanshoe.kalendarkit.component.text.KalendarTitle
import com.himanshoe.kalendarkit.component.text.config.KalendarTextColor
import com.himanshoe.kalendarkit.component.text.config.KalendarTextConfig
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import java.util.*

private val headerColors = listOf(
    Color(0xFFC39EA1),
    Color(0xFFBB8D9E),
    Color(0xFFAA8FB1),
    Color(0xFF9E94B4),
    Color(0xFF9599B4),
    Color(0xFF91ABC5),
    Color(0xFF8CB2C6),
    Color(0xFF8CB7BE),
    Color(0xFF8BACA9),
    Color(0xFF9DB39A),
    Color(0xFFADBA9A),
    Color(0xFFBEC196),
)

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

fun getTextColor(month: Month) = KalendarTextColor(headerColors[month.value.minus(1)])

internal fun getTitleText(monthName: String, year: Int): String {
    return monthName.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    } + " " + year
}
