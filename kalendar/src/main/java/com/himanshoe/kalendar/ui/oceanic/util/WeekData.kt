package com.himanshoe.kalendar.ui.oceanic.util

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

private const val TOTAL_DAYS_IN_WEEK = 7
internal fun LocalDate.getNext7Dates() =
    List(TOTAL_DAYS_IN_WEEK) { day -> this.plus(day.toLong(), DateTimeUnit.DAY) }

internal fun LocalDate.getPrevious7Dates() =
    List(TOTAL_DAYS_IN_WEEK) { day -> this.minus((day + 1), DateTimeUnit.DAY) }.reversed()

@SuppressWarnings("MagicNumber")
fun Int.isLeapYear(): Boolean {
    return (this % 400 == 0) || (this % 4 == 0 && this % 100 != 0)
}
