package com.himanshoe.kalendar.endlos.util

object CalendarMonthSource {

    internal fun getInitialMonths(): List<Int> {
        return mutableListOf<Int>().apply {
            (0..5).forEach {
                add(it)
            }
        }
    }

    internal fun next6Months(index: Int): List<Int> {
        return mutableListOf<Int>().apply {
            (index.plus(1)..index.plus(6)).forEach {
                add(it)
            }
        }
    }
}
