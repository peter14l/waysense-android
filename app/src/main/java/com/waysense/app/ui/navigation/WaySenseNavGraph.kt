package com.waysense.app.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.waysense.app.ui.screens.help.HelpScreen
import com.waysense.app.ui.screens.home.HomeScreen
import com.waysense.app.ui.screens.journey.ActiveJourneyScreen
import com.waysense.app.ui.screens.journeys.JourneysScreen
import com.waysense.app.ui.screens.map.MapScreen
import com.waysense.app.ui.screens.onboarding.OnboardingScreen
import com.waysense.app.ui.screens.profile.ProfileScreen
import com.waysense.app.ui.screens.route.RoutePlanningScreen
import com.waysense.app.ui.screens.search.SearchScreen
import com.waysense.app.ui.screens.settings.AccessibilitySettingsScreen
import com.waysense.app.ui.screens.station.StationDetailsScreen

@Composable
fun WaySenseApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = currentDestination?.route in WaySenseScreen.bottomNavRoutes.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        val selected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.contentDescription,
                                )
                            },
                            label = { Text(item.label) },
                            selected = selected,
                            colors = NavigationBarItemDefaults.colors(),
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            modifier = Modifier.semantics {
                                contentDescription = item.contentDescription
                            },
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = WaySenseScreen.Onboarding.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                fadeIn(animationSpec = tween(300)) + slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(300),
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(300),
                )
            },
        ) {
            composable(WaySenseScreen.Onboarding.route) {
                OnboardingScreen(
                    onOnboardingComplete = {
                        navController.navigate(WaySenseScreen.Home.route) {
                            popUpTo(WaySenseScreen.Onboarding.route) { inclusive = true }
                        }
                    },
                )
            }

            composable(WaySenseScreen.Home.route) {
                HomeScreen(
                    onSearchClick = { navController.navigate(WaySenseScreen.Search.route) },
                    onStationClick = { stationId ->
                        navController.navigate(WaySenseScreen.RoutePlanning.createRoute(stationId))
                    },
                    onStationDetailsClick = { stationId ->
                        navController.navigate(WaySenseScreen.StationDetails.createRoute(stationId))
                    },
                )
            }

            composable(WaySenseScreen.Search.route) {
                SearchScreen(
                    onStationSelected = { stationId ->
                        navController.navigate(WaySenseScreen.RoutePlanning.createRoute(stationId))
                    },
                    onBack = { navController.popBackStack() },
                )
            }

            composable(
                route = WaySenseScreen.RoutePlanning.route,
                arguments = listOf(navArgument("stationId") { type = NavType.StringType }),
            ) { backStackEntry ->
                val stationId = backStackEntry.arguments?.getString("stationId") ?: ""
                RoutePlanningScreen(
                    stationId = stationId,
                    onStartJourney = { journeyId ->
                        navController.navigate(WaySenseScreen.ActiveJourney.createRoute(journeyId))
                    },
                    onViewMap = { sid ->
                        navController.navigate(WaySenseScreen.MapOverview.createRoute(sid))
                    },
                    onStationDetails = { sid ->
                        navController.navigate(WaySenseScreen.StationDetails.createRoute(sid))
                    },
                    onBack = { navController.popBackStack() },
                )
            }

            composable(
                route = WaySenseScreen.MapOverview.route,
                arguments = listOf(navArgument("stationId") { type = NavType.StringType }),
            ) {
                MapScreen(
                    onBack = { navController.popBackStack() },
                )
            }

            composable(
                route = WaySenseScreen.StationDetails.route,
                arguments = listOf(navArgument("stationId") { type = NavType.StringType }),
            ) { backStackEntry ->
                val stationId = backStackEntry.arguments?.getString("stationId") ?: ""
                StationDetailsScreen(
                    stationId = stationId,
                    onBack = { navController.popBackStack() },
                )
            }

            composable(
                route = WaySenseScreen.ActiveJourney.route,
                arguments = listOf(navArgument("journeyId") { type = NavType.StringType }),
            ) {
                ActiveJourneyScreen(
                    onJourneyComplete = {
                        navController.navigate(WaySenseScreen.Home.route) {
                            popUpTo(WaySenseScreen.Home.route) { inclusive = true }
                        }
                    },
                    onHelp = {
                        navController.navigate(WaySenseScreen.Help.route)
                    },
                    onBack = { navController.popBackStack() },
                )
            }

            composable(WaySenseScreen.Journeys.route) {
                JourneysScreen(
                    onJourneyClick = { journeyId ->
                        navController.navigate(WaySenseScreen.JourneyDetails.createRoute(journeyId))
                    },
                )
            }

            composable(
                route = WaySenseScreen.JourneyDetails.route,
                arguments = listOf(navArgument("journeyId") { type = NavType.StringType }),
            ) {
                JourneysScreen(
                    onJourneyClick = { },
                )
            }

            composable(WaySenseScreen.AccessibilitySettings.route) {
                AccessibilitySettingsScreen()
            }

            composable(WaySenseScreen.Profile.route) {
                ProfileScreen()
            }

            composable(WaySenseScreen.Help.route) {
                HelpScreen(
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}
