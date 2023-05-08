package com.himanshoe.kalendar.endlos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.endlos.model.KalendarDayKonfig
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.model.KalendarTextKonfig
import com.himanshoe.kalendar.endlos.paging.KalendarViewModel
import com.himanshoe.kalendar.endlos.ui.KalendarMonth
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.time.Month

@Composable
fun KalendarEndlos(
    currentDay: LocalDate?,
    modifier: Modifier = Modifier,
    kalendarViewModel: KalendarViewModel = viewModel(),
    showLabel: Boolean = true,
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    events: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
) {

    KalendarEndloss(
        modifier = modifier,
        kalendarItems = kalendarViewModel.getDates().collectAsLazyPagingItems(),
        currentDay = currentDay,
        showLabel = showLabel,
        events = events,
        dayContent = dayContent,
        onDayClick = onDayClick,
        headerContent = headerContent,
        kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
        kalendarColors = kalendarColors,
        kalendarDayKonfig = kalendarDayKonfig,
    )
}

@Composable
private fun KalendarEndloss(
    kalendarItems: LazyPagingItems<LocalDate>,
    currentDay: LocalDate?,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    events: KalendarEvents = KalendarEvents(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
) {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val clickedDay = remember { mutableStateOf(today) }
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(kalendarItems) { date: LocalDate? ->
            if (date != null) {
                val kalendarColor = if (kalendarColors.color.count() == 1) {
                    kalendarColors.color.first()
                } else kalendarColors.color[date.monthNumber.minus(1)]

                KalendarMonth(
                    currentDay = currentDay,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .background(kalendarColor.backgroundColor),
                    events = events,
                    onDayClick = { day, events ->
                        clickedDay.value = day
                        onDayClick(day, events)
                    },
                    showLabel = showLabel,
                    kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
                    kalendarDayKonfig = kalendarDayKonfig,
                    dayContent = dayContent,
                    headerContent = headerContent,
                )
            }
        }
    }
}