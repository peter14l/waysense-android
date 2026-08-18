package com.waysense.app.data.mock

import com.waysense.app.data.model.AccessibilityFeature
import com.waysense.app.data.model.Station
import com.waysense.app.data.model.StationType

object MockStations {

    val stations = listOf(
        Station(
            id = "park_street",
            name = "Park Street Metro",
            type = StationType.Metro,
            distanceMeters = 850,
            accessibilityScore = 4.6f,
            features = listOf(
                AccessibilityFeature("Step-free entrance", true, "elevator"),
                AccessibilityFeature("Elevator", true, "elevator"),
                AccessibilityFeature("Tactile paving", true, "touch_app"),
                AccessibilityFeature("Audio announcements", true, "volume_up"),
                AccessibilityFeature("Accessible restroom", true, "wc"),
            ),
            unavailableFeatures = listOf("Braille signage at Gate 3"),
            lastVerified = "2 days ago",
            latitude = 22.5530,
            longitude = 88.3510,
            lines = listOf("Blue Line", "Green Line"),
            gates = listOf("Gate 1", "Gate 2", "Gate 3"),
        ),
        Station(
            id = "esplanade",
            name = "Esplanade Metro Station",
            type = StationType.Metro,
            distanceMeters = 1800,
            accessibilityScore = 4.8f,
            features = listOf(
                AccessibilityFeature("Step-free entrance", true, "elevator"),
                AccessibilityFeature("Elevator", true, "elevator"),
                AccessibilityFeature("Tactile paving", true, "touch_app"),
                AccessibilityFeature("Audio announcements", true, "volume_up"),
                AccessibilityFeature("Accessible restroom", true, "wc"),
                AccessibilityFeature("Braille signage", true, "braille"),
            ),
            unavailableFeatures = emptyList(),
            lastVerified = "1 day ago",
            latitude = 22.5550,
            longitude = 88.3470,
            lines = listOf("Blue Line"),
            gates = listOf("Exit 1", "Exit 2", "Exit 3"),
        ),
        Station(
            id = "sealdah",
            name = "Sealdah Station",
            type = StationType.Railway,
            distanceMeters = 2400,
            accessibilityScore = 3.9f,
            features = listOf(
                AccessibilityFeature("Step-free entrance", true, "elevator"),
                AccessibilityFeature("Audio announcements", true, "volume_up"),
                AccessibilityFeature("Tactile paving", true, "touch_app"),
            ),
            unavailableFeatures = listOf("Accessible restroom", "Braille signage"),
            lastVerified = "5 days ago",
            latitude = 22.5680,
            longitude = 88.3660,
            lines = listOf("Suburban Railway"),
            gates = listOf("Platform 1", "Platform 4", "Platform 8"),
        ),
        Station(
            id = "howrah",
            name = "Howrah Station",
            type = StationType.Railway,
            distanceMeters = 4200,
            accessibilityScore = 3.5f,
            features = listOf(
                AccessibilityFeature("Audio announcements", true, "volume_up"),
                AccessibilityFeature("Tactile paving", true, "touch_app"),
            ),
            unavailableFeatures = listOf("Step-free entrance", "Elevator", "Accessible restroom"),
            lastVerified = "1 week ago",
            latitude = 22.5850,
            longitude = 88.3470,
            lines = listOf("Suburban Railway", "Long Distance"),
            gates = listOf("Platform 1", "Platform 5", "Platform 10"),
        ),
        Station(
            id = "salt_lake",
            name = "Salt Lake Sector V",
            type = StationType.Metro,
            distanceMeters = 3100,
            accessibilityScore = 4.3f,
            features = listOf(
                AccessibilityFeature("Step-free entrance", true, "elevator"),
                AccessibilityFeature("Elevator", true, "elevator"),
                AccessibilityFeature("Tactile paving", true, "touch_app"),
                AccessibilityFeature("Audio announcements", true, "volume_up"),
            ),
            unavailableFeatures = listOf("Accessible restroom"),
            lastVerified = "3 days ago",
            latitude = 22.5700,
            longitude = 88.4320,
            lines = listOf("Purple Line"),
            gates = listOf("Gate A", "Gate B"),
        ),
    )

    fun getById(id: String): Station? = stations.find { it.id == id }

    fun getNearby(): List<Station> = stations.take(3)
}
