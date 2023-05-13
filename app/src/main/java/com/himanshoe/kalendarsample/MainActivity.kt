package com.himanshoe.kalendarsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.endlos.Kalendar
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendarsample.ui.theme.KalendarTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KalendarTheme {
                Column {
                    val events = (0..5).map {
                        KalendarEvent(
                            date = Clock.System.todayIn(
                                TimeZone.currentSystemDefault()
                            ).plus(it, DateTimeUnit.DAY),
                            eventName = it.toString(),
                        )
                    }
                    val events1 = (0..5).map {
                        com.himanshoe.kalendar.KalendarEvent(
                            date = Clock.System.todayIn(
                                TimeZone.currentSystemDefault()
                            ).plus(it, DateTimeUnit.DAY),
                            eventName = it.toString(),
                        )
                    }
//                    com.himanshoe.kalendar.Kalendar(
//                        currentDay = Clock.System.todayIn(
//                            TimeZone.currentSystemDefault()
//                        ),
//                        kalendarType = KalendarType.Oceanic,
//                        events = com.himanshoe.kalendar.KalendarEvents(events1+events1+events1)
//
//                    )
//                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
//                    com.himanshoe.kalendar.Kalendar(
//                        currentDay = Clock.System.todayIn(
//                            TimeZone.currentSystemDefault()
//                        ),
//                        kalendarType = KalendarType.Firey,
//                        events = com.himanshoe.kalendar.KalendarEvents(events1+events1+events1)
//                    )
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))

                    Kalendar(
                        modifier = Modifier.fillMaxSize(),
                        events = KalendarEvents(events + events)
                    )
                }
            }
        }
    }
}

