package com.himanshoe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalendar(kalendarType = KalendarType.Oceanic(),
                kalendarStyle = KalendarStyle(
                    kalendarSelector = KalendarSelector.Dot()
                ),
                onCurrentDayClick = { date, event ->

                },
                errorMessage = {})
        }
    }
}
