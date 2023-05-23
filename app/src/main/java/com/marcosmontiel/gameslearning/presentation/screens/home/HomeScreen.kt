package com.marcosmontiel.gameslearning.presentation.screens.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.marcosmontiel.gameslearning.presentation.navigation.HomeNavGraph
import com.marcosmontiel.gameslearning.presentation.navigation.HomeRoutes
import com.marcosmontiel.gameslearning.presentation.navigation.HomeRoutes.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
@ExperimentalCoroutinesApi
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        content = {

            HomeNavGraph(navController = navController)

        },
        bottomBar = {

            BottomBar(navController = navController)

        }
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Posts,
        MyPosts,
        Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation {
            screens.forEach { screen ->

                AddItemToBottomBar(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )

            }
        }
    }
}

@Composable
fun RowScope.AddItemToBottomBar(
    screen: HomeRoutes,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(route = screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "navigation icon"
            )
        },
        label = {
            Text(text = screen.title)
        },
        unselectedContentColor = LocalContentColor.current.copy(
            alpha = ContentAlpha.disabled
        )
    )
}
