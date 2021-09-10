package by.akella.benefits.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import by.akella.benefits.ui.navigation.NavGraph
import by.akella.benefits.ui.navigation.Screens
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BenefitsApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBarNavigator(navController) }
    ) { innerPadding ->
        NavGraph(navController, modifier = Modifier.padding(innerPadding))
    }
}