package com.himanshoe.kalendar.ui.oceanic.util

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

internal fun LocalDate.getNext7Dates() = buildList {
    repeat(7) { day ->
        add(this@getNext7Dates.plus(day, DateTimeUnit.DAY))
    }
}

internal fun LocalDate.getPrevious7Dates() = buildList {
    repeat(7) { day ->
        add(this@getPrevious7Dates.minus(day.plus(1), DateTimeUnit.DAY))
    }
}.reversed()

fun Int.isLeapYear(): Boolean {
    val year = this
    return when {
        ((year % 400 == 0) || (((year % 4) == 0) && ((year % 100) != 0))) -> true
        else -> false
    }
}

fun getDatesBetween(start: LocalDate, end: LocalDate): MutableList<LocalDate> {
    var date = start
    val dates = mutableListOf<LocalDate>()
    while (date < (end)) {
        dates.add(date)
        date = date.plus(1, DateTimeUnit.DAY)
    }
    dates.add(end)
    return dates
}
