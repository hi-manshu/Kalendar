package com.himanshoe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.himanshoe.kalendar.endlos.ui.Kalendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalendar(
                onCurrentDayClick = { date, event ->
                },
                errorMessage = {}
            )
        }
    }
}
