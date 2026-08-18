package com.waysense.app.data.model

data class UserProfile(
    val name: String = "Alex",
    val voiceGuidance: Boolean = true,
    val vibrationGuidance: Boolean = true,
    val highContrast: Boolean = false,
    val largeText: Boolean = false,
    val simplifiedInterface: Boolean = true,
    val announceStationNames: Boolean = true,
    val repeatImportantAlerts: Boolean = true,
)
