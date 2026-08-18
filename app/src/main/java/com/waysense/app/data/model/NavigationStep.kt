package com.waysense.app.data.model

data class NavigationStep(
    val stepNumber: Int,
    val instruction: String,
    val distanceMeters: Int,
    val landmark: String?,
    val accessibilityNote: String?,
    val isCurrentStep: Boolean = false,
)
