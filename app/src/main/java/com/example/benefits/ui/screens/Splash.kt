package com.example.benefits.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.benefits.R
import com.example.benefits.ui.navigation.Screens
import com.example.benefits.ui.theme.Blue700
import kotlinx.coroutines.delay

@Composable
fun Splash(navigateTo: (String) -> Unit) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "logo",
            colorFilter = ColorFilter.tint(Blue700)
        )
    }

    LaunchedEffect(Screens.Splash) {
        delay(2000L)
        navigateTo(Screens.HomeScreens.List.screenName)
    }
}


@Preview
@Composable
fun preview() {
    Splash {}
}