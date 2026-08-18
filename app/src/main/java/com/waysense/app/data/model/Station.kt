package com.waysense.app.data.model

data class Station(
    val id: String,
    val name: String,
    val type: StationType,
    val distanceMeters: Int,
    val accessibilityScore: Float,
    val features: List<AccessibilityFeature>,
    val unavailableFeatures: List<String>,
    val lastVerified: String,
    val latitude: Double,
    val longitude: Double,
    val lines: List<String>,
    val gates: List<String>,
)

enum class StationType {
    Metro,
    Bus,
    Railway,
    Landmark,
}
