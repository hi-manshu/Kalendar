package com.himanshoe.kalendar.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.config.KalendarKonfig
import com.himanshoe.kalendar.ui.firey.KalendarFirey
import com.himanshoe.kalendar.ui.oceanic.KalendarOceanic
import java.time.LocalDate

sealed class KalendarType {
    object Fire : KalendarType()
    object Oceanic : KalendarType()
}

@Composable
fun Kalendar(
    kalendarType: KalendarType, modifier: Modifier = Modifier,
    kalendarKonfig: KalendarKonfig = KalendarKonfig(),
    startDate: LocalDate = LocalDate.now(),
    onCurrentDayClick: (LocalDate) -> Unit,
) {
    when (kalendarType) {
        is KalendarType.Fire -> KalendarFirey(modifier, kalendarKonfig, onCurrentDayClick)
        is KalendarType.Oceanic -> KalendarOceanic(
            modifier,
            kalendarKonfig,
            startDate,
            onCurrentDayClick
        )
        else -> KalendarFirey(modifier, kalendarKonfig, onCurrentDayClick)
    }
}
