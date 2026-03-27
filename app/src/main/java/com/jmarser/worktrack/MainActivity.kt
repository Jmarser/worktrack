package com.jmarser.worktrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.worktrack.ui.theme.AppDimens
import com.jmarser.worktrack.ui.theme.LocalDimens
import com.jmarser.worktrack.ui.theme.WorkTrackTheme
import com.jmarser.worktrack.ui.theme.appDimens
import com.jmarser.worktrack.ui.theme.shapes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkTrackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(appDimens.paddingMedium)
    ){
        Box(
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight()
                .background(
                    color = MaterialTheme.colorScheme.error,
                    shape = MaterialTheme.shapes.extraLarge
                )
                .align(Alignment.CenterStart)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .offset(x = appDimens.paddingNormal)
                .padding(end = appDimens.paddingNormal)
                .background(
                    color = Color.Transparent
                ),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = appDimens.cardElevationNormal)
        ) { }
    }
}

@Preview(
    showBackground = false
)
@Composable
fun GreetingPreview() {
    WorkTrackTheme {
        Greeting("Android")
    }
}