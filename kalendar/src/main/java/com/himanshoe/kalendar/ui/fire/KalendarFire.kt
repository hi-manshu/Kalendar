package com.himanshoe.kalendar.ui.fire

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarTheme
import com.himanshoe.kalendar.config.KalendarKonfig
import java.time.LocalDate

@Composable
fun KalendarFire(
    modifier: Modifier = Modifier,
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
    onCurrentDayClick: (LocalDate) -> Unit,
) {
    val haptic = LocalHapticFeedback.current

    KalendarTheme {
        val color = kalendarKonfig.backgroundColor ?: KalendarTheme.colors.generalDisabled
        val calendarBackgroundColor =
            kalendarKonfig.calendarColor ?: KalendarTheme.colors.background
        Card(
            modifier = modifier
                .background(color)
                .padding(Grid.OneHalf),
            shape = kalendarKonfig.shape,
            elevation = kalendarKonfig.elevation,
            backgroundColor = calendarBackgroundColor,
        ) {
            KalendarView(
                onCurrentDayClick = { date ->
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onCurrentDayClick(date)
                }
            )
        }
    }
}
