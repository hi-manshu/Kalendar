package com.himanshoe.kalendar.ui.ocean

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.himanshoe.design.primitive.texts.Demibold
import com.himanshoe.design.primitive.texts.KalendarText
import com.himanshoe.design.primitive.texts.Regular
import com.himanshoe.design.theme.Grid
import com.himanshoe.design.theme.KalendarTheme
import com.himanshoe.kalendar.config.KalendarKonfig
import com.himanshoe.kalendar.ui.fire.KalendarWeekDayNames
import java.time.LocalDate

@Composable
fun KalendarOcean(
    modifier: Modifier = Modifier,
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
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
            shape = kalendarKonfig.bottonCurved,
            elevation = kalendarKonfig.elevation,
            backgroundColor = calendarBackgroundColor,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Grid.OneHalf),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Grid.OneHalf),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    KalendarText.Text4.Regular(
                        modifier = Modifier.alpha(0.5F),
                        text = "Today",
                        maxLines = 1,
                        textAlign = TextAlign.End,
                        color = KalendarTheme.colors.black
                    )
                    KalendarText.Text4.Demibold(
                        text = "Today",
                        maxLines = 1,
                        textAlign = TextAlign.End,
                        color = KalendarTheme.colors.black
                    )
                }
                // Handle the Calendar Per Week
                KalendarOceanWeek()
            }
        }
    }
}

@Preview
@Composable
fun KalendarOceanPreview(
    modifier: Modifier = Modifier,
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
) {
    KalendarOcean {}
}
