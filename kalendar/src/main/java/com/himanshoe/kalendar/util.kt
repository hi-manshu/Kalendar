package com.himanshoe.kalendar

enum class DayOfWeek(val value: Int) {
    Sunday(0),
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6),
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
