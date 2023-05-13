package com.himanshoe.kalendar.endlos.paging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.paging.Pager
import androidx.paging.PagingConfig

/**
 * Controller for managing the paging functionality of Kalendar items.
 */
class KalendarPagingController {

    private val repository = KalendarRepository()

    /**
     * Flow of Kalendar items loaded from the repository using the Paging library.
     */
    val kalendarItems = Pager(PagingConfig(pageSize = 12)) {
        KalendarPagingSource(repository)
    }.flow
}

/**
 * Remembers an instance of [KalendarPagingController].
 *
 * @return The remembered [KalendarPagingController] instance.
 */
@Composable
fun rememberKalendarPagingController(): KalendarPagingController {
    return remember {
        KalendarPagingController()
    }
}

