package com.example.benefits.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.benefits.ui.screens.*

@Composable
fun Graph(initialScreen: Screens) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBarNavigator(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = initialScreen.screenName,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.Splash.screenName) {
                Splash { navController.navigate(Screens.List.screenName) }
            }
            composable(Screens.List.screenName) {
                Benefits { route -> navController.navigate(route) }
            }
            composable(Screens.News.screenName) {
                News()
            }
            composable(Screens.Map.screenName) {
                BenefitMap()
            }
            composable(Screens.Settings.screenName) {
                Settings()
            }
            composable(
                Screens.Details.screenName,
                arguments = listOf(navArgument("modelId") { type = NavType.StringType })
            ) {
                Details(modelId = it.arguments?.getString("modelId", "-1") ?: "-1") {
                    navController.navigateUp()
                }
            }
        }
    }
}

sealed class Screens(val screenName: String) {
    object Splash : Screens("splash")
    object List : Screens("list")
    object News : Screens("news")
    object Map : Screens("map")
    object Settings : Screens("settings")

    object Details : Screens("details/{modelId}") {
        fun createRoute(modelId: String) = "details/$modelId"
    }
}