package com.himanshoe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.himanshoe.kalendar.ui.Kalendar

class MainActivity : ComponentActivity() {
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kalendar {}
        }
    }
}
