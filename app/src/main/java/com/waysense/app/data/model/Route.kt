package com.waysense.app.data.model

data class Route(
    val id: String,
    val originName: String,
    val destinationName: String,
    val transportMode: TransportMode,
    val durationMinutes: Int,
    val walkingDistanceMeters: Int,
    val transfers: Int,
    val accessibilityRating: AccessibilityRating,
    val isRecommended: Boolean,
    val steps: List<RouteStep>,
)

enum class TransportMode {
    Metro,
    Bus,
    Walking,
    Mixed,
}

enum class AccessibilityRating {
    Excellent,
    Good,
    Fair,
}

data class RouteStep(
    val stepNumber: Int,
    val instruction: String,
    val durationSeconds: Int,
    val distanceMeters: Int,
    val transportMode: TransportMode,
    val landmark: String?,
    val accessibilityNote: String?,
)
