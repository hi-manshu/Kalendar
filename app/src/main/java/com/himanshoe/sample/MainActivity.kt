package com.himanshoe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.himanshoe.kalendar.endlos.common.KalendarKonfig
import com.himanshoe.kalendar.endlos.common.data.KalendarEvent
import com.himanshoe.kalendar.endlos.ui.Kalendar
import java.time.DayOfWeek
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalendar(
                kalendarEvents = listOf(KalendarEvent(LocalDate.now().plusDays(3), "", "")),
                kalendarKonfig = KalendarKonfig(weekCharacters = 2, firstDayOfWeek = DayOfWeek.MONDAY),
                onCurrentDayClick = { date, event ->
                },
                errorMessage = {}
            )
        }
    }
}
