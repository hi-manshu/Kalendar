package com.himanshoe.sample

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.himanshoe.kalendar.common.KalendarKonfig
import com.himanshoe.kalendar.common.data.KalendarEvent
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType
import java.time.LocalDate

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreenUI(){

    val pagerState = rememberPagerState()

    HorizontalPager(
        count = 3,
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ){
        when(it){

            /**
             * Demonstrate the use of [Kalendar] calendar
             */
            0 -> {
                Kalendar(
                    kalendarEvents = listOf(
                        KalendarEvent(
                            date = LocalDate.now().plusDays(3),
                            eventName = "",
                            eventDescription = ""
                        )
                    ),
                    kalendarKonfig = KalendarKonfig(
                        weekCharacters = 2,
                    ),
                    onCurrentDayClick = { date, events ->
                    },
                    errorMessage = {},
                    kalendarType = KalendarType.Oceanic()
                )
            }


            /**
             * Demonstrate the use of [KalendarType.Firey] calendar
             */
            1 -> {
                Kalendar(
                    kalendarType = KalendarType.Firey(),
                    onCurrentDayClick = { day, event ->
                        //handle the day click listener
                    },
                    errorMessage = {
                        //Handle the error if any
                    }
                )
            }

            /**
             * Demonstrate the use of [KalendarType.Oceanic] calendar
             */
            2 -> {
                Kalendar(
                    kalendarType = KalendarType.Oceanic(),
                    onCurrentDayClick = { day, event ->
                        //handle the day click listener
                    },
                    errorMessage = {
                        //Handle the error if any
                    }
                )
            }
        }
    }
}