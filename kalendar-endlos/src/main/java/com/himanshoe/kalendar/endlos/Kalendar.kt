package com.himanshoe.kalendar.endlos

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.himanshoe.kalendar.endlos.color.KalendarColors
import com.himanshoe.kalendar.endlos.color.KalendarThemeColor
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayColors
import com.himanshoe.kalendar.endlos.component.day.config.KalendarDayDefaultColors
import com.himanshoe.kalendar.endlos.model.KalendarDay
import com.himanshoe.kalendar.endlos.model.KalendarEvent

@Composable
fun Kalendar(
    modifier: Modifier = Modifier,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    kalendarThemeColors: List<KalendarThemeColor> = KalendarColors.defaultColors(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    kalendarDayColors: KalendarDayColors = KalendarDayDefaultColors.defaultColors(),
) {
    val kalendarViewModel: KalendarViewModel = viewModel()
    KalendarEndloss(
        modifier = modifier,
        kalendarItems = kalendarViewModel.getDates().collectAsLazyPagingItems(),
        kalendarEvents = kalendarEvents,
        onCurrentDayClick = onCurrentDayClick,
        kalendarDayColors = kalendarDayColors,
        kalendarThemeColors = kalendarThemeColors,
    )
}

@Composable
fun Kalendar(
    modifier: Modifier = Modifier,
    kalendarThemeColor: KalendarThemeColor,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    kalendarDayColors: KalendarDayColors = KalendarDayDefaultColors.defaultColors(),
) {
    val kalendarViewModel: KalendarViewModel = viewModel()
    KalendarEndloss(
        modifier = modifier,
        kalendarItems = kalendarViewModel.getDates().collectAsLazyPagingItems(),
        kalendarEvents = kalendarEvents,
        onCurrentDayClick = onCurrentDayClick,
        kalendarDayColors = kalendarDayColors,
        kalendarThemeColors = listOf(kalendarThemeColor),
    )
}
