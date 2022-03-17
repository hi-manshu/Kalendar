package com.himanshoe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.himanshoe.kalendar.endlos.common.KalendarKonfig
import com.himanshoe.kalendar.endlos.common.data.KalendarEvent
import com.himanshoe.kalendar.endlos.ui.Kalendar
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalendar(
                kalendarEvents = listOf(
                    KalendarEvent(LocalDate.now().plusDays(3), "Test event one", "Test one description"),
                    KalendarEvent(LocalDate.now().plusDays(3), "Test event two", "Test two description")
                ),
                kalendarKonfig = KalendarKonfig(weekCharacters = 2),
                onCurrentDayClick = { date, events ->
                },
                errorMessage = {}
            )
        }
    }
}
