package com.waysense.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.outlined.Accessibility
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Route
import androidx.compose.ui.graphics.vector.ImageVector

data class WaySenseBottomNavItem(
    val screen: WaySenseScreen,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val contentDescription: String,
)

val bottomNavItems = listOf(
    WaySenseBottomNavItem(
        screen = WaySenseScreen.Home,
        label = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        contentDescription = "Home",
    ),
    WaySenseBottomNavItem(
        screen = WaySenseScreen.Journeys,
        label = "Journeys",
        selectedIcon = Icons.Filled.Route,
        unselectedIcon = Icons.Outlined.Route,
        contentDescription = "Journeys",
    ),
    WaySenseBottomNavItem(
        screen = WaySenseScreen.AccessibilitySettings,
        label = "Accessibility",
        selectedIcon = Icons.Filled.Accessibility,
        unselectedIcon = Icons.Outlined.Accessibility,
        contentDescription = "Accessibility settings",
    ),
    WaySenseBottomNavItem(
        screen = WaySenseScreen.Profile,
        label = "Profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        contentDescription = "Profile",
    ),
)
