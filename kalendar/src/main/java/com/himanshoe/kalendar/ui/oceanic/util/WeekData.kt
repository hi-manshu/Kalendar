package com.himanshoe.kalendar.ui.oceanic.util

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

private const val TOTAL_DAYS_IN_WEEK = 7

/**
 * Returns the next 7 dates starting from the current date.
 * @return A list of the next 7 dates.
 */
internal fun LocalDate.getNext7Dates() =
    List(TOTAL_DAYS_IN_WEEK) { day -> this.plus(day.toLong(), DateTimeUnit.DAY) }

/**
 * Returns the previous 7 dates before the current date.
 * @return A list of the previous 7 dates.
 */
internal fun LocalDate.getPrevious7Dates() =
    List(TOTAL_DAYS_IN_WEEK) { day -> this.minus((day + 1), DateTimeUnit.DAY) }.reversed()

/**
 * Checks if the year is a leap year.
 * @return True if the year is a leap year, false otherwise.
 */
@SuppressWarnings("MagicNumber")
fun Int.isLeapYear(): Boolean {
    return (this % 400 == 0) || (this % 4 == 0 && this % 100 != 0)
}
