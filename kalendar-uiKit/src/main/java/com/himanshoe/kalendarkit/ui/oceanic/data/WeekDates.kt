package com.himanshoe.kalendarkit.ui.oceanic.data

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

internal fun LocalDate.getNext7Dates(): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    repeat(7) { day ->
        dates.add(this.plus(day, DateTimeUnit.DAY))
    }
    return dates
}

internal fun LocalDate.getPrevious7Dates(): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    repeat(7) { day ->
        dates.add(this.minus(day.plus(1), DateTimeUnit.DAY))
    }
    return dates.reversed()
}
