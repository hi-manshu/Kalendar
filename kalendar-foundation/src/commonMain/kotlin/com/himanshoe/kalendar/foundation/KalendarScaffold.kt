package com.himanshoe.kalendar.foundation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.foundation.component.config.KalendarDayLabelKonfig
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate

@Composable
fun KalendarScaffold(
    showDayLabel: Boolean,
    dayOfWeek: () -> List<DayOfWeek>,
    kalendarDayLabelKonfig: KalendarDayLabelKonfig,
    modifier: Modifier = Modifier,
    dates: () -> List<LocalDate>,
    content: @Composable (LocalDate) -> Unit,
) {
    val displayDates = dates()
    val displayDayOfWeek = dayOfWeek()

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(7),
        horizontalArrangement = Arrangement.Center,
        content = {
            if (showDayLabel) {
                items(displayDayOfWeek) { dayOfWeek ->
                    Text(
                        text = dayOfWeek.name.take(kalendarDayLabelKonfig.textCharCount),
                        modifier = Modifier.fillMaxWidth(),
                        style = kalendarDayLabelKonfig.textStyle
                    )
                }
            }
            items(items = displayDates) { date ->
                content(date)
            }
        }
    )
}
