package com.himanshoe.kalendar

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.himanshoe.kalendar.component.day.config.KalendarDayConfig
import com.himanshoe.kalendar.component.day.config.KalendarDayDefaults
import com.himanshoe.kalendar.config.KalendarConfigDefaults
import com.himanshoe.kalendar.config.KalendarConfigs
import com.himanshoe.kalendar.model.KalendarDay
import com.himanshoe.kalendar.model.KalendarEvent
import com.himanshoe.kalendar.model.KalendarType
import com.himanshoe.kalendar.ui.firey.KalendarFirey
import com.himanshoe.kalendar.ui.oceanic.KalendarOceanic
import kotlinx.datetime.LocalDate

@Composable
fun Kalendar(
    modifier: Modifier = Modifier,
    takeMeToDate: LocalDate? = null,
    kalendarType: KalendarType = KalendarType.Oceanic,
    kalendarEvents: List<KalendarEvent> = emptyList(),
    onCurrentDayClick: (KalendarDay, List<KalendarEvent>) -> Unit = { _, _ -> },
    kalendarDayConfig: KalendarDayConfig = KalendarDayDefaults.kalendarDayConfig(),
    kalendarConfigs: KalendarConfigs = KalendarConfigDefaults.kalendarConfigDefaults()
) {
    when (kalendarType) {
        KalendarType.Oceanic -> KalendarOceanic(
            modifier = modifier.wrapContentHeight(),
            kalendarEvents = kalendarEvents,
            onCurrentDayClick = onCurrentDayClick,
            kalendarDayConfig = kalendarDayConfig,
            kalendarConfigs = kalendarConfigs,
            takeMeToDate = takeMeToDate
        )
        KalendarType.Firey -> {
            KalendarFirey(
                modifier = modifier.wrapContentHeight(),
                kalendarEvents = kalendarEvents,
                onCurrentDayClick = onCurrentDayClick,
                kalendarDayConfig = kalendarDayConfig,
                kalendarConfigs = kalendarConfigs,
                takeMeToDate = takeMeToDate
            )
        }
    }
}

@Composable
@Preview
private fun KalendarPreview() {
    Kalendar()
}
