package by.akella.benefits.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.compose.rememberNavController
import by.akella.benefits.ui.navigation.NavGraph
import by.akella.benefits.ui.navigation.TestNavGraph
import io.github.aakira.napier.Napier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BenefitsApp() {
    val navController = rememberNavController()
//
//    TestNavGraph(navController = navController)

    Scaffold(
        bottomBar = { BottomBarNavigator(navController) }
    ) { innerPadding ->
        NavGraph(navController, modifier = Modifier.padding(innerPadding))
    }
}