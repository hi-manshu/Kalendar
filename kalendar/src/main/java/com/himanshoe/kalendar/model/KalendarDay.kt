package com.himanshoe.kalendar.model

import kotlinx.datetime.LocalDate

@JvmInline
value class KalendarDay(val localDate: LocalDate)

fun LocalDate.toKalendarDay() = KalendarDay(this)
