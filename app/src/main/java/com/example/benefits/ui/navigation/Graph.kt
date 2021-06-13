package com.example.benefits.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.benefits.ui.screens.Benefits
import com.example.benefits.ui.screens.Details

@Composable
fun Graph(initialScreen: Screens) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = initialScreen.screenName
    ) {
        composable(Screens.Main.screenName) {
            Benefits { route -> navController.navigate(route) }
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

sealed class Screens(val screenName: String) {
    object Main : Screens("main")
    object Details : Screens("details/{modelId}") {
        fun createRoute(modelId: String) = "details/$modelId"
    }
}