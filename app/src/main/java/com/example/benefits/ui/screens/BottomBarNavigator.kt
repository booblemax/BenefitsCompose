package com.example.benefits.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.benefits.R
import com.example.benefits.ui.navigation.Screens

@Composable
fun BottomBarNavigator(navController: NavController) {
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        BottomNavigationItem(
            currentDestination?.hierarchy?.any { it.route == Screens.List.screenName } == true,
            onClick = { navController.navigate(Screens.List.screenName) },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_list),
                    context.getString(R.string.navigation_tab_list)
                )
            },
            label = { Text(context.getString(R.string.navigation_tab_list)) }
        )
        BottomNavigationItem(
            currentDestination?.hierarchy?.any { it.route == Screens.News.screenName } == true,
            onClick = { navController.navigate(Screens.News.screenName) },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_news),
                    context.getString(R.string.navigation_tab_news)
                )
            },
            label = { Text(context.getString(R.string.navigation_tab_news)) }
        )
        BottomNavigationItem(
            currentDestination?.hierarchy?.any { it.route == Screens.Map.screenName } == true,
            onClick = { navController.navigate(Screens.Map.screenName) },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_map),
                    context.getString(R.string.navigation_tab_map)
                )
            },
            label = { Text(context.getString(R.string.navigation_tab_map)) }
        )
        BottomNavigationItem(
            currentDestination?.hierarchy?.any { it.route == Screens.Settings.screenName } == true,
            onClick = { navController.navigate(Screens.Settings.screenName) },
            icon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_settings),
                    context.getString(R.string.navigation_tab_settings)
                )
            },
            label = { Text(context.getString(R.string.navigation_tab_settings)) }
        )
    }
}