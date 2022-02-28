package by.akella.benefits.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import by.akella.benefits.ui.viewmodels.AuthState
import by.akella.benefits.ui.viewmodels.SplashViewModel
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.getViewModel

@Composable
fun TestNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "test1") {
        composable("test1") {
            Napier.i { "test1 recomposition" }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "test1")
                val viewModel = getViewModel<SplashViewModel>()
                val state by viewModel.authState.collectAsState(AuthState.Loading)
                if (state is AuthState.SignedIn) navController.navigate("test2")
                LaunchedEffect(key1 = viewModel) {
                    viewModel.checkIsAuth()
                }
            }
        }
        composable("test2") {
            Napier.i { "test2 recomposition" }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "test2")
            }
        }
    }
}