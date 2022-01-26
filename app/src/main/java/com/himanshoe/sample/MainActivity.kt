package com.himanshoe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.endlos.common.KalendarKonfig
import com.himanshoe.kalendar.endlos.common.KalendarStyle
import com.himanshoe.kalendar.endlos.ui.Kalendar


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalendar(
                kalendarKonfig = KalendarKonfig(2023),
                kalendarStyle = KalendarStyle(
                    kalendarSelector = KalendarSelector.Dot()
                ),
                onCurrentDayClick = { date, event ->

                },
                errorMessage = {})
        }
    }
}
