package com.himanshoe.kalendarkit.ui.oceanic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendarkit.component.KalendarHeader
import com.himanshoe.kalendarkit.component.day.KalendarDay
import com.himanshoe.kalendarkit.component.day.config.KalendarDayConfig
import com.himanshoe.kalendarkit.config.KalendarConfigs
import com.himanshoe.kalendarkit.model.KalendarDay
import com.himanshoe.kalendarkit.model.KalendarEvent
import com.himanshoe.kalendarkit.model.toKalendarDay
import com.himanshoe.kalendarkit.ui.oceanic.data.getNext7Dates
import com.himanshoe.kalendarkit.ui.oceanic.data.getPrevious7Dates
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import kotlinx.datetime.TimeZone

@Composable
fun KalendarOceanic(
    kalendarConfigs: KalendarConfigs,
    kalendarDayConfig: KalendarDayConfig,
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
) {
    val currentDay = Clock.System.todayIn(TimeZone.currentSystemDefault())

    val weekValue = remember { mutableStateOf(currentDay.getNext7Dates()) }
    val monthName = weekValue.value.first().month.name
    val selectedKalendarDate = remember { mutableStateOf(currentDay) }

    Column(
        modifier = modifier
            .background(color = kalendarConfigs.background)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        KalendarHeader(
            modifier = Modifier.fillMaxWidth(),
            monthName = monthName,
            onPreviousClick = {
                val firstDayOfDisplayedWeek = weekValue.value.first()
                weekValue.value = firstDayOfDisplayedWeek.getPrevious7Dates()
            },
            onNextClick = {
                val lastDayOfDisplayedWeek = weekValue.value.last().plus(1, DateTimeUnit.DAY)
                weekValue.value = lastDayOfDisplayedWeek.getNext7Dates()
            }
        )
        Row(modifier = Modifier.wrapContentWidth()) {
            weekValue.value.forEach { localDate ->
                val isCurrentDay = localDate == currentDay

                KalendarDay(
                    modifier = Modifier,
                    isCurrentDay = isCurrentDay,
                    kalendarDay = localDate.toKalendarDay(),
                    kalendarEvents = kalendarEvents,
                    onCurrentDayClick = { kalendarDay, events ->
                        selectedKalendarDate.value = kalendarDay.localDate
                        onCurrentDayClick(kalendarDay, events)
                    },
                    kalendarDayConfig = kalendarDayConfig,
                    selectedKalendarDay = selectedKalendarDate.value
                )
            }
        }
    }
}
