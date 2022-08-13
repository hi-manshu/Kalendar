package com.himanshoe.kalendarkit.model

import kotlinx.datetime.LocalDate

@JvmInline
value class KalendarDay(val localDate: LocalDate)

fun LocalDate.toKalendarDay() = KalendarDay(this)
