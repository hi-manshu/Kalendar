package com.himanshoe.kalendar.endlos.component.day.config

sealed interface KalendarDayState {
    object KalendarDaySelected : KalendarDayState
    object KalendarDayDefault : KalendarDayState
}
