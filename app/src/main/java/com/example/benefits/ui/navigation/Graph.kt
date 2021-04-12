package com.example.benefits.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.ui.screens.Benefits
import com.example.benefits.ui.screens.Details
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun Graph(initialScreen: Screen) {
    val scope = rememberCoroutineScope()
    val graphState = remember { GraphState(initialScreen, scope) }

    val screenState =
        graphState.backStackState.collectAsState(initialScreen, scope.coroutineContext)

    FactoryScreen(graphState, screen = screenState.value)
}

class GraphState(
    initialScreen: Screen,
    private val coroutineScope: CoroutineScope
) : Router {

    private val stackState = MutableSharedFlow<Screen>()
    val backStackState: SharedFlow<Screen> get() = stackState

    private val backStack = mutableListOf(initialScreen)

    override fun navigateTo(screen: Screen) {
        backStack.add(screen)
        coroutineScope.launch { stackState.emit(screen) }
    }

    override fun back() {
        backStack.removeFirst()
        if (backStack.isNotEmpty()) {
            navigateTo(backStack.first())
        } else {
            navigateTo(Screen.Exit)
        }
    }
}

@Composable
fun FactoryScreen(router: Router, screen: Screen) {
    when (screen) {
        is Screen.DetailsScreen -> Details(router, model = screen.model)
        Screen.MainScreen -> Benefits(router)
        Screen.Exit -> { }
    }
}

interface Router {
    fun navigateTo(screen: Screen)
    fun back()
}

sealed class Screen {
    object MainScreen : Screen()
    data class DetailsScreen(val model: BenefitModel) : Screen()
    object Exit : Screen()
}