package com.himanshoe.kalendar.util


internal fun Int.validateMaxDate(year: Int): Boolean {
    return if (year == 0) {
        true
    } else {
        year > this
    }
}

internal fun Int.validateMinDate(year: Int): Boolean {
    return if (year == 0) {
        true
    } else {
        year < this
    }
}
