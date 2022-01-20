package com.himanshoe.kalendar.ui.firey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarTheme
import com.himanshoe.kalendar.common.KalendarKonfig
import com.himanshoe.kalendar.common.KalendarStyle
import java.time.LocalDate

@Composable
internal fun KalendarFirey(
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
    kalendarStyle: KalendarStyle = KalendarStyle(),
    selectedDay: LocalDate = LocalDate.now(),
    onCurrentDayClick: (LocalDate) -> Unit,
    errorMessageLogged: (String) -> Unit,
) {

    KalendarTheme {
        val color = kalendarStyle.kalendarBackgroundColor ?: KalendarTheme.colors.generalDisabled
        val calendarBackgroundColor =
            kalendarStyle.kalendarColor ?: KalendarTheme.colors.background
        Card(
            modifier = Modifier
                .background(color)
                .padding(Grid.OneHalf),
            shape = kalendarStyle.shape,
            elevation = kalendarStyle.elevation,
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
