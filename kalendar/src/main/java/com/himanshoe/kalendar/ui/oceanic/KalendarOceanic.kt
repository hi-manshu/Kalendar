package com.himanshoe.kalendar.ui.oceanic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarTheme
import com.himanshoe.kalendar.common.KalendarKonfig
import com.himanshoe.kalendar.common.KalendarStyle
import java.time.LocalDate

@Composable
internal fun KalendarOceanic(
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
    kalendarStyle: KalendarStyle = KalendarStyle(),
    startDate: LocalDate = LocalDate.now(),
    selectedDay: LocalDate = startDate,
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
                .padding(bottom = Grid.One),
            shape = kalendarStyle.shape,
            elevation = kalendarStyle.elevation,
            backgroundColor = calendarBackgroundColor,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Grid.OneHalf),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                KalendarOceanWeek(
                    yearRange = kalendarKonfig.yearRange,
                    startDate = startDate,
                    selectedDay = selectedDay,
                    onCurrentDayClick = onCurrentDayClick,
                    errorMessageLogged = errorMessageLogged
                )
            }
        }
    }
}

@Preview
@Composable
private fun KalendarOceanPreview(
    modifier: Modifier = Modifier,
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
) {
    KalendarOceanic(onCurrentDayClick = {
    }, errorMessageLogged = {})
}
