package com.waysense.app.data.model

data class Journey(
    val id: String,
    val originName: String,
    val destinationName: String,
    val durationMinutes: Int,
    val date: String,
    val isCompleted: Boolean,
    val steps: List<NavigationStep>,
    val transportMode: TransportMode,
)
