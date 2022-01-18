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
import com.himanshoe.kalendar.config.KalendarKonfig
import java.time.LocalDate

@Composable
internal fun KalendarOceanic(
    modifier: Modifier = Modifier,
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
    startDate: LocalDate = LocalDate.now(),
    onCurrentDayClick: (LocalDate) -> Unit
) {
    KalendarTheme {
        val color = kalendarKonfig.backgroundColor ?: KalendarTheme.colors.generalDisabled
        val calendarBackgroundColor =
            kalendarKonfig.calendarColor ?: KalendarTheme.colors.background

        Card(
            modifier = modifier
                .background(color)
                .padding(bottom = Grid.One),
            shape = kalendarKonfig.bottomCurved,
            elevation = kalendarKonfig.elevation,
            backgroundColor = calendarBackgroundColor,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Grid.OneHalf),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                KalendarOceanWeek(
                    startDate = startDate,
                    onCurrentDayClick = onCurrentDayClick
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
    KalendarOceanic {
    }
}
