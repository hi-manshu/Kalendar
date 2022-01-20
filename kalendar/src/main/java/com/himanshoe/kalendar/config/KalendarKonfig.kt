package com.himanshoe.kalendar.config

/**
 * [KalendarKonfig] represents the config needed for rendering calendar
 * @param[yearRange] gives the min/max year range
 */
data class KalendarKonfig(
    val yearRange: YearRange = YearRange(),
)

/**
 * [YearRange] represents range from
 * [min] years to
 * [max] years
 */
data class YearRange(val min: Int = 0, val max: Int = 0)
