package com.example.benefits.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.benefits.ui.navigation.Screens
import com.example.benefits.ui.theme.Teal200
import kotlinx.coroutines.delay

@Composable
fun Splash(navigateToList: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "MBICYCLE BENEFITS",
            color = Teal200,
            fontWeight = FontWeight.ExtraBold,
            style = TextStyle(
                Color.Blue,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
    }

    LaunchedEffect(key1 = Screens.Splash.screenName) {
        delay(1500L)
        navigateToList()
    }
}

@Preview
@Composable
fun preview() {
    Splash { }
}

