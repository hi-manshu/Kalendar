package com.himanshoe.kalendarkit.component.day.config

sealed interface KalendarDayState {
    object KalendarDaySelected : KalendarDayState
    object KalendarDayDefault : KalendarDayState
}
