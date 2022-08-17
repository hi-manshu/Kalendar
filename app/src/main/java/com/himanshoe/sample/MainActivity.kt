package com.himanshoe.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigator = rememberNavController()
            RegisterNavigation(navigator = navigator,
                onEndlossClicked = {
                    navigator.navigate("endloss")
                }, onKalendarClicked = {
                    navigator.navigate("kalendar")
                }
            )
        }
    }
}

@Composable
fun KalendarApp(onEndlossClicked: () -> Unit, onKalendarClicked: () -> Unit) {
    val list: List<Pair<String, () -> Unit>> =
        listOf(
            "Kalendar" to onKalendarClicked,
            "Kalendar Endloss" to onEndlossClicked,
        )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        items(list) { item ->
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                onClick = item.second
            ) {
                Text(item.first)
            }
        }
    }
}
