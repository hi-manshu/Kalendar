package com.himanshoe.kalendarkit.ui.oceanic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendarkit.component.button.KalendarIconButton
import com.himanshoe.kalendarkit.component.day.KalendarDay
import com.himanshoe.kalendarkit.component.day.config.KalendarDayConfig
import com.himanshoe.kalendarkit.component.day.config.KalendarDayDefaults
import com.himanshoe.kalendarkit.component.text.KalendarSubTitle
import com.himanshoe.kalendarkit.model.KalendarDay
import com.himanshoe.kalendarkit.model.KalendarEvent
import com.himanshoe.kalendarkit.model.toKalendarDay
import com.himanshoe.kalendarkit.ui.oceanic.config.KalendarOceanicConfigs
import com.himanshoe.kalendarkit.ui.oceanic.config.KalendarOceanicConfigsDefaults
import com.himanshoe.kalendarkit.ui.oceanic.data.getNext7Dates
import com.himanshoe.kalendarkit.ui.oceanic.data.getPrevious7Dates
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.util.*

@Composable
fun KalendarOceanic(
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    kalendarDayConfig: KalendarDayConfig = KalendarDayDefaults.kalendarDayConfig(),
    kalendarOceanicConfigs: KalendarOceanicConfigs = KalendarOceanicConfigsDefaults.kalendarOceanicConfigsDefaults()
) {
    val currentDay = Clock.System.todayIn(TimeZone.currentSystemDefault())

    val weekValue = remember { mutableStateOf(currentDay.getNext7Dates()) }
    val monthName = weekValue.value.first().month.name
    val selectedKalendarDate = remember { mutableStateOf(currentDay) }

    Column(
        modifier = modifier
            .background(color = kalendarOceanicConfigs.background)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        OceanicHeader(
            modifier = Modifier.fillMaxWidth(),
            monthName = monthName,
            onPreviousWeekClick = {
                val firstDayOfDisplayedWeek = weekValue.value.first()
                weekValue.value = firstDayOfDisplayedWeek.getPrevious7Dates()
            },
            onNextWeekClick = {
                val lastDayOfDisplayedWeek = weekValue.value.last().plus(1, DateTimeUnit.DAY)
                weekValue.value = lastDayOfDisplayedWeek.getNext7Dates()
            }
        )
        Row(modifier = Modifier.wrapContentWidth()) {
            weekValue.value.forEach { localDate ->
                val isCurrentDay = localDate == currentDay

                KalendarDay(
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

@Composable
private fun OceanicHeader(
    modifier: Modifier,
    monthName: String,
    onPreviousWeekClick: () -> Unit = {},
    onNextWeekClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 8.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        KalendarSubTitle(
            text = getTitleText(monthName),
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .align(Alignment.CenterVertically)
        )

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterVertically),
            horizontalArrangement = Arrangement.End,
        ) {
            KalendarIconButton(
                modifier = Modifier.wrapContentSize(),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Previous Week",
                onClick = { onPreviousWeekClick() }

            )
            KalendarIconButton(
                modifier = Modifier.wrapContentSize(),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next Month",
                onClick = { onNextWeekClick() }
            )
        }
    }
}

fun getTitleText(monthName: String): String {
    return monthName.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }

}
