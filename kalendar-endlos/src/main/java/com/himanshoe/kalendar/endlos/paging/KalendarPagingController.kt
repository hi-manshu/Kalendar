package com.himanshoe.kalendar.endlos.paging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.paging.Pager
import androidx.paging.PagingConfig

class KalendarPagingController {

    val repository = KalendarRepository()

    val kalendarItems =
        Pager(PagingConfig(pageSize = 12)) {
            KalendarPagingSource(repository)
        }.flow

}

@Composable
fun rememberKalendarPagingController(): KalendarPagingController {
    return remember {
        KalendarPagingController()
    }
}
