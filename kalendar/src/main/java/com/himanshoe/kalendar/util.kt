package com.himanshoe.kalendar

sealed class DayOfWeek(val value: Int) {
    object Sunday : DayOfWeek(0)
    object Monday : DayOfWeek(1)
    object Tuesday : DayOfWeek(2)
    object Wednesday : DayOfWeek(3)
    object Thursday : DayOfWeek(4)
    object Friday : DayOfWeek(5)
    object Saturday : DayOfWeek(6)
}

val monthNames = listOf(
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
)

fun getDayOfWeek(dayOfWeek: Int): DayOfWeek = when (dayOfWeek) {
    1 -> DayOfWeek.Sunday
    2 -> DayOfWeek.Monday
    3 -> DayOfWeek.Tuesday
    4 -> DayOfWeek.Wednesday
    5 -> DayOfWeek.Thursday
    6 -> DayOfWeek.Friday
    7 -> DayOfWeek.Saturday
    else -> throw IllegalArgumentException("dayOfWeek must be in range [1..7]")
}
