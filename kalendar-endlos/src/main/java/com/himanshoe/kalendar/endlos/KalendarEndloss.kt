package com.himanshoe.kalendar.endlos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.himanshoe.kalendar.endlos.color.KalendarThemeColor
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.endlos.component.header.config.KalendarHeaderConfig
import com.himanshoe.kalendar.endlos.component.text.config.KalendarTextColor
import com.himanshoe.kalendar.endlos.component.text.config.KalendarTextConfig
import com.himanshoe.kalendar.endlos.component.text.config.KalendarTextSize
import com.himanshoe.kalendar.endlos.model.KalendarDay
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
internal fun KalendarEndloss(
    modifier: Modifier = Modifier,
    kalendarItems: LazyPagingItems<LocalDate>,
    kalendarEvents: List<KalendarEvent>,
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit,
    kalendarDayColors: KalendarDayColors,
    kalendarThemeColors: List<KalendarThemeColor>,
    kalendarHeaderConfig: KalendarHeaderConfig = KalendarHeaderConfig(
        kalendarTextConfig = KalendarTextConfig(
            kalendarTextColor = KalendarTextColor(kalendarDayColors.textColor),
            kalendarTextSize = KalendarTextSize.SubTitle
        )
    ),
) {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val clickedDay = remember { mutableStateOf(today) }

    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(kalendarItems) { date: LocalDate? ->
            if (date != null) {
                val kalendarThemeColor = if (kalendarThemeColors.count() == 1) {
                    kalendarThemeColors.first()
                } else kalendarThemeColors[date.monthNumber.minus(1)]

                KalendarMonth(
                    date = date,
                    modifier = modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .background(kalendarThemeColor.backgroundColor),
                    kalendarEvents = kalendarEvents.filter { it.date.monthNumber == date.monthNumber },
                    onCurrentDayClick = { day, events ->
                        clickedDay.value = day.localDate
                        onCurrentDayClick(day, events)
                    },
                    kalendarDayColors = kalendarDayColors,
                    selectedDay = clickedDay.value,
                    kalendarThemeColors = kalendarThemeColor,
                    kalendarHeaderConfig = kalendarHeaderConfig
                )
            }
        }
    }
}
