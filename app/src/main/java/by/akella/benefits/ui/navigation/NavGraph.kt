package by.akella.benefits.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import by.akella.benefits.ui.screens.*

@OptIn(ExperimentalUnitApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.Splash.screenName,
    ) {
        composable(Screens.Splash.screenName) {
            Splash { route ->
                navController.navigate(route) { popUpTo(route) { inclusive = true } }
            }
        }
        navigation(
            route = Screens.Home.screenName,
            startDestination = Screens.HomeScreens.List.screenName
        ) {
            composable(Screens.HomeScreens.List.screenName) {
                Benefits { route -> navController.navigate(route) }
            }
            composable(Screens.HomeScreens.Map.screenName) {
                BenefitMap()
            }
            composable(Screens.HomeScreens.Card.screenName) {
                Card { route ->
                    navController.navigate(route) {
                        popUpTo(route) { inclusive = true }
                    }
                }
            }
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
    object Splash : Screens("splash")
    object Home : Screens("mainScreen")
    sealed class HomeScreens(screenName: String) : Screens(screenName) {
        object List : Screens("list")
        object Map : Screens("map")
        object Card : Screens("settings")
    }

    object Details : Screens("details/{modelId}") {
        fun createRoute(modelId: String) = "details/$modelId"
    }
}