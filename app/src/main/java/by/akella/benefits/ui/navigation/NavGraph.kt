package by.akella.benefits.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import by.akella.benefits.ui.screens.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalAnimationApi
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
                navController.navigate(route) { popUpTo(Screens.Splash.screenName) { inclusive = true } }
            }
        }
        navigation(
            route = Screens.Home.screenName,
            startDestination = Screens.HomeScreens.List.screenName
        ) {
            composable(Screens.HomeScreens.List.screenName) {
                ApplyDefaultSystemBarsColors()
                Benefits { route -> navController.navigate(route) }
            }
            composable(Screens.HomeScreens.Map.screenName) {
                ApplyDefaultSystemBarsColors()
                BenefitMap()
            }
            composable(Screens.HomeScreens.Card.screenName) {
                Card { route: String ->
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
            ApplyDefaultSystemBarsColors()
            Details(modelId = it.arguments?.getString("modelId", "-1") ?: "-1") {
                navController.navigateUp()
            }
        }
    }

}

@Composable
fun ApplyDefaultSystemBarsColors() {
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colors.primaryVariant
    val color1 = MaterialTheme.colors.primary
    LaunchedEffect(key1 = "systemBars") {
        systemUiController.setStatusBarColor(color)
        systemUiController.setNavigationBarColor(color1)
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