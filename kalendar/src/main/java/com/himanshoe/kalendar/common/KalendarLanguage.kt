package com.himanshoe.kalendar.common

import java.util.*

sealed class KalendarLanguage(val local: Locale,val days: List<String>) {
    object English : KalendarLanguage(local = Locale.ENGLISH, days = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"))
    object French : KalendarLanguage(local = Locale.FRENCH, days = listOf("Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam"))
}