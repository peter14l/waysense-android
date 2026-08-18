package com.waysense.app.data.mock

import com.waysense.app.data.model.AccessibilityRating
import com.waysense.app.data.model.Route
import com.waysense.app.data.model.RouteStep
import com.waysense.app.data.model.TransportMode

object MockRoutes {

    fun getRoutesToStation(destinationId: String): List<Route> {
        return when (destinationId) {
            "esplanade" -> listOf(
                Route(
                    id = "metro_esplanade",
                    originName = "Current location",
                    destinationName = "Esplanade Metro Station",
                    transportMode = TransportMode.Metro,
                    durationMinutes = 24,
                    walkingDistanceMeters = 450,
                    transfers = 1,
                    accessibilityRating = AccessibilityRating.Excellent,
                    isRecommended = true,
                    steps = listOf(
                        RouteStep(1, "Walk to Park Street Metro", 300, 450, TransportMode.Walking, "Park Street", "Use tactile path along sidewalk"),
                        RouteStep(2, "Enter through accessible Gate 2", 60, 30, TransportMode.Walking, "Gate 2", "Step-free entrance available"),
                        RouteStep(3, "Take Blue Line toward Dakshineswar", 1080, 0, TransportMode.Metro, "Platform 2", "Audio announcements at each stop"),
                        RouteStep(4, "Exit at Esplanade", 60, 20, TransportMode.Walking, "Esplanade", "Use Exit 3 elevator"),
                    ),
                ),
                Route(
                    id = "bus_esplanade",
                    originName = "Current location",
                    destinationName = "Esplanade Metro Station",
                    transportMode = TransportMode.Bus,
                    durationMinutes = 31,
                    walkingDistanceMeters = 250,
                    transfers = 0,
                    accessibilityRating = AccessibilityRating.Good,
                    isRecommended = false,
                    steps = listOf(
                        RouteStep(1, "Walk to bus stop", 180, 250, TransportMode.Walking, "Park Street Bus Stop", null),
                        RouteStep(2, "Take Bus 22 toward Esplanade", 1500, 0, TransportMode.Bus, "Bus 22", "Low-floor bus with ramp"),
                        RouteStep(3, "Alight at Esplanade", 60, 50, TransportMode.Walking, "Esplanade", null),
                    ),
                ),
            )
            "park_street" -> listOf(
                Route(
                    id = "walk_parkstreet",
                    originName = "Current location",
                    destinationName = "Park Street Metro",
                    transportMode = TransportMode.Walking,
                    durationMinutes = 10,
                    walkingDistanceMeters = 850,
                    transfers = 0,
                    accessibilityRating = AccessibilityRating.Good,
                    isRecommended = true,
                    steps = listOf(
                        RouteStep(1, "Walk straight along Park Street", 480, 500, TransportMode.Walking, null, "Flat sidewalk with tactile paving"),
                        RouteStep(2, "Turn left toward metro entrance", 180, 200, TransportMode.Walking, "Park Street Metro", null),
                        RouteStep(3, "Arrive at Gate 2", 120, 150, TransportMode.Walking, null, "Step-free entrance"),
                    ),
                ),
            )
            "sealdah" -> listOf(
                Route(
                    id = "metro_sealdah",
                    originName = "Current location",
                    destinationName = "Sealdah Station",
                    transportMode = TransportMode.Mixed,
                    durationMinutes = 28,
                    walkingDistanceMeters = 600,
                    transfers = 1,
                    accessibilityRating = AccessibilityRating.Good,
                    isRecommended = true,
                    steps = listOf(
                        RouteStep(1, "Walk to Park Street Metro", 300, 450, TransportMode.Walking, "Park Street Metro", "Use tactile path"),
                        RouteStep(2, "Take Green Line to Sealdah", 720, 0, TransportMode.Metro, "Platform 4", "Audio announcements"),
                        RouteStep(3, "Exit and walk to Sealdah Station", 180, 150, TransportMode.Walking, "Sealdah Station", null),
                    ),
                ),
            )
            else -> listOf(
                Route(
                    id = "default_route",
                    originName = "Current location",
                    destinationName = "Destination",
                    transportMode = TransportMode.Mixed,
                    durationMinutes = 20,
                    walkingDistanceMeters = 300,
                    transfers = 0,
                    accessibilityRating = AccessibilityRating.Good,
                    isRecommended = true,
                    steps = listOf(
                        RouteStep(1, "Walk to nearest station", 300, 300, TransportMode.Walking, null, null),
                    ),
                ),
            )
        }
    }
}
