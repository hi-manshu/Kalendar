package com.himanshoe.sample

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.himanshoe.sample.demo.KalendarDemo
import com.himanshoe.sample.demo.KalendarEndlossDemo

@Composable
fun RegisterNavigation(
    navigator: NavHostController,
    onEndlossClicked: () -> Unit,
    onKalendarClicked: () -> Unit,
) {
    NavHost(navController = navigator, startDestination = "main") {
        composable("endloss") { KalendarEndlossDemo() }
        composable("main") {
            KalendarApp(
                onEndlossClicked,
                onKalendarClicked,
            )
        }
        composable("kalendar") { KalendarDemo() }
    }
}
