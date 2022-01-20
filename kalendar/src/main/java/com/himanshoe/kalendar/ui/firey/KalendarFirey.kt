package com.himanshoe.kalendar.ui.firey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarShape
import com.himanshoe.design.theme.KalendarTheme
import com.himanshoe.kalendar.config.KalendarKonfig
import java.time.LocalDate

@Composable
internal fun KalendarFirey(
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
    selectedDay: LocalDate = LocalDate.now(),
    onCurrentDayClick: (LocalDate) -> Unit,
    errorMessageLogged: (String) -> Unit,
) {

    KalendarTheme {
        val color = kalendarKonfig.backgroundColor ?: KalendarTheme.colors.generalDisabled
        val calendarBackgroundColor =
            kalendarKonfig.calendarColor ?: KalendarTheme.colors.background
        Card(
            modifier = Modifier
                .background(color)
                .padding(Grid.OneHalf),
            shape = kalendarKonfig.shape,
            elevation = kalendarKonfig.elevation,
            backgroundColor = calendarBackgroundColor,
        ) {
            KalendarView(
                yearRange = kalendarKonfig.yearRange,
                errorMessageLogged = errorMessageLogged,
                selectedDay = selectedDay,
                onCurrentDayClick = { date ->
                    onCurrentDayClick(date)
                }
            )
        }
    }
}
