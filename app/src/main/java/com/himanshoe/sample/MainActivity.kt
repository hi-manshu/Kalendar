package com.himanshoe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.himanshoe.kalendar.common.KalendarKonfig
import com.himanshoe.kalendar.common.data.KalendarEvent
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalendar(
                kalendarType = KalendarType.Oceanic(),
                kalendarEvents = listOf(KalendarEvent(LocalDate.now().plusDays(3), "", "")),
                kalendarKonfig = KalendarKonfig(weekCharacters = 2),
                onCurrentDayClick = { date, event ->
                },
                errorMessage = {}
            )
        }
    }
}
