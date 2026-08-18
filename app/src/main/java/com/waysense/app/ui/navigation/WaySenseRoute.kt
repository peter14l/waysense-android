package com.waysense.app.ui.navigation

sealed class WaySenseScreen(val route: String) {
    data object Onboarding : WaySenseScreen("onboarding")
    data object Home : WaySenseScreen("home")
    data object Search : WaySenseScreen("search")
    data object RoutePlanning : WaySenseScreen("route_planning/{stationId}") {
        fun createRoute(stationId: String) = "route_planning/$stationId"
    }
    data object MapOverview : WaySenseScreen("map_overview/{stationId}") {
        fun createRoute(stationId: String) = "map_overview/$stationId"
    }
    data object StationDetails : WaySenseScreen("station_details/{stationId}") {
        fun createRoute(stationId: String) = "station_details/$stationId"
    }
    data object ActiveJourney : WaySenseScreen("active_journey/{journeyId}") {
        fun createRoute(journeyId: String) = "active_journey/$journeyId"
    }
    data object Journeys : WaySenseScreen("journeys")
    data object JourneyDetails : WaySenseScreen("journey_details/{journeyId}") {
        fun createRoute(journeyId: String) = "journey_details/$journeyId"
    }
    data object AccessibilitySettings : WaySenseScreen("accessibility_settings")
    data object Profile : WaySenseScreen("profile")
    data object Help : WaySenseScreen("help")

    companion object {
        val bottomNavRoutes = listOf(Home, Journeys, AccessibilitySettings, Profile)
    }
}
