package com.himanshoe.kalendarkit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.himanshoe.kalendarkit.component.day.config.KalendarDayConfig
import com.himanshoe.kalendarkit.component.day.config.KalendarDayDefaults
import com.himanshoe.kalendarkit.config.KalendarConfigDefaults
import com.himanshoe.kalendarkit.config.KalendarConfigs
import com.himanshoe.kalendarkit.model.KalendarDay
import com.himanshoe.kalendarkit.model.KalendarEvent
import kotlinx.datetime.LocalDate

@Composable
fun Kalendar(
    modifier: Modifier = Modifier,
    takeMeToDate: LocalDate? = null,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    kalendarDayConfig: KalendarDayConfig = KalendarDayDefaults.kalendarDayConfig(),
    kalendarConfigs: KalendarConfigs = KalendarConfigDefaults.kalendarConfigDefaults()
) {
    val calendarViewModel = KalendarViewModel()
    KalendarEndloss(
        modifier = modifier,
        calendarItems = calendarViewModel.getCalendarData().collectAsLazyPagingItems(),
        takeMeToDate = takeMeToDate,
        kalendarEvents = kalendarEvents,
        onCurrentDayClick = onCurrentDayClick,
        kalendarConfigs = kalendarConfigs,
        kalendarDayConfig = kalendarDayConfig,
    )
}

@Composable
private fun KalendarEndloss(
    modifier: Modifier,
    calendarItems: LazyPagingItems<LocalDate>,
    takeMeToDate: LocalDate?,
    kalendarEvents: List<KalendarEvent>,
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit,
    kalendarConfigs: KalendarConfigs,
    kalendarDayConfig: KalendarDayConfig = KalendarDayDefaults.kalendarDayConfig(),
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(calendarItems) { date: LocalDate? ->
            if (date != null) {
                val background = kalendarConfigs.getBackground(date.monthNumber)
                KalendarMonth(
                    date = date,
                    modifier = modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                        .background(background),
                    takeMeToDate = takeMeToDate,
                    kalendarEvents = kalendarEvents,
                    onCurrentDayClick = onCurrentDayClick,
                    kalendarDayConfig = kalendarDayConfig,
                )
            }
        }
    }
}
