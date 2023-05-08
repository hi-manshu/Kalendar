package com.himanshoe.kalendarsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.himanshoe.kalendar.endlos.KalendarEndlos
import com.himanshoe.kalendarsample.ui.theme.KalendarTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KalendarTheme {
                Column {
//                    KalendarOceanic(
//                        currentDay = Clock.System.todayIn(
//                            TimeZone.currentSystemDefault()
//                        ),
//                    )
//                    Spacer(modifier = Modifier.padding(vertical = 16.dp))
//                    KalendarFirey(
//                        currentDay = Clock.System.todayIn(
//                            TimeZone.currentSystemDefault()
//                        ),
//                    )
                    KalendarEndlos(
                        modifier = Modifier.fillMaxSize(),
                        currentDay = Clock.System.todayIn(
                            TimeZone.currentSystemDefault()
                        ),
                    )
                }
            }
        }
    }
}

