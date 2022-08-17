package com.himanshoe.kalendar.endlos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayConfig
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayDefaults
import com.himanshoe.kalendar.endlos.config.KalendarConfigDefaults
import com.himanshoe.kalendar.endlos.config.KalendarConfigs
import com.himanshoe.kalendar.endlos.model.KalendarDay
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun Kalendar(
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    kalendarDayConfig: KalendarDayConfig = KalendarDayDefaults.kalendarDayConfig(),
    kalendarConfigs: KalendarConfigs = KalendarConfigDefaults.kalendarConfigDefaults(),
) {
    val kalendarViewModel: KalendarViewModel = viewModel()
    KalendarEndloss(
        modifier = modifier,
        kalendarItems = kalendarViewModel.getDates().collectAsLazyPagingItems(),
        kalendarEvents = kalendarEvents,
        onCurrentDayClick = onCurrentDayClick,
        kalendarConfigs = kalendarConfigs,
        kalendarDayConfig = kalendarDayConfig,
    )
}

@Composable
private fun KalendarEndloss(
    modifier: Modifier,
    kalendarItems: LazyPagingItems<LocalDate>,
    kalendarEvents: List<KalendarEvent>,
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit,
    kalendarConfigs: KalendarConfigs,
    kalendarDayConfig: KalendarDayConfig = KalendarDayDefaults.kalendarDayConfig(),
) {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val clickedDay = remember { mutableStateOf(today) }

    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(kalendarItems) { date: LocalDate? ->
            if (date != null) {
                val background = kalendarConfigs.getBackground(date.monthNumber)
                KalendarMonth(
                    date = date,
                    modifier = modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .background(background),
                    kalendarEvents = kalendarEvents,
                    onCurrentDayClick = { day, events ->
                        clickedDay.value = day.localDate
                        onCurrentDayClick(day, events)
                    },
                    kalendarDayConfig = kalendarDayConfig,
                    selectedDay = clickedDay.value,
                )
            }
        }
    }
}
