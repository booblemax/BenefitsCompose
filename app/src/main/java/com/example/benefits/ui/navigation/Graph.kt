package com.example.benefits.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.ui.screens.Benefits
import com.example.benefits.ui.screens.Details
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun Graph(initialScreen: Screens) {
    val navController = rememberNavController()
    val router = NavRouter(navController)

    NavHost(
        navController = navController,
        startDestination = initialScreen.screenName
    ) {
        composable(Screens.MAIN.screenName) { Benefits(router) }
        composable(
            "${Screens.DETAILS.screenName}/{model}",
            arguments = listOf(navArgument("model") { type = NavType.StringType })
        ) {
            Details(router = router, model = it.arguments?.getString("model", "-1"))
        }
    }
}

interface Router {

    fun navigateTo(screen: Screens, launchSingleTop: Boolean = false)
    fun back()
}

enum class Screens(val screenName: String) {
    MAIN("main"),
    DETAILS("details")
}

class NavRouter(private val navController: NavController) : Router {

    override fun navigateTo(screen: Screens, launchSingleTop: Boolean, arguments: Map<String, Any> = emptyMap()) {
        if (arguments.isNotEmpty()) {
            Navigation
        }
        navController.navigate(screen.toString()) {
            this.launchSingleTop = launchSingleTop
        }
    }

    override fun back() {
        navController.popBackStack()
    }
}