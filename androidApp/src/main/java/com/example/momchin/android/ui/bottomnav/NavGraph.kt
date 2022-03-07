package com.example.momchin.android.ui.bottomnav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.example.momchin.android.ui.bung.BungDetailContent
import com.example.momchin.android.ui.bung.BungListContent
import com.example.momchin.android.ui.community.CommunityDetailContent
import com.example.momchin.android.ui.community.CommunityListContent
import com.example.momchin.presentation.main.MoMChinMain

sealed class Direction(val route: String) {
    object Community : Direction("Community")
    object Bung : Direction("Bung")
    object Messages : Direction("Messages")
    object Profile : Direction("Profile")
}

@Composable
fun BottomNavContent(component: MoMChinMain) {
    val navController = rememberNavController()
    val items = listOf(
        Icons.Default.Home to Direction.Community.route,
        Icons.Default.LocationOn to Direction.Bung.route,
        Icons.Default.Email to Direction.Messages.route,
        Icons.Default.Person to Direction.Profile.route
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                fun navigate(route: String) = navController.navigate(route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }

                items.forEach { (icon, route) ->
                    BottomNavigationItem(
                        selected = currentRoute == route,
                        onClick = { navigate(route) },
                        icon = { Icon(imageVector = icon, contentDescription = null) }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Direction.Community.route
        ) {
            addComposableDestinations(component)
        }
    }
}

private fun NavGraphBuilder.addComposableDestinations(component: MoMChinMain) {
    composable(Direction.Community.route) {
        Children(routerState = component.communityRouterState) {
            when (val child = it.instance) {
                is MoMChinMain.CommunityChild.List -> CommunityListContent(child.component)
                is MoMChinMain.CommunityChild.Detail -> CommunityDetailContent(child.component)
            }
        }
    }
    composable(Direction.Bung.route) {
        Children(routerState = component.bungRouterState) {
            when (val child = it.instance) {
                is MoMChinMain.BungChild.List -> BungListContent(child.component)
                is MoMChinMain.BungChild.Detail -> BungDetailContent(child.component)
            }
        }
    }
    composable(Direction.Messages.route) {
    }
    composable(Direction.Profile.route) {
    }
}