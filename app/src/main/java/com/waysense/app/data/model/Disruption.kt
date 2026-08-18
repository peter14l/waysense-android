package com.waysense.app.data.model

data class Disruption(
    val id: String,
    val title: String,
    val description: String,
    val affectedStation: String,
    val alternativeSuggestion: String,
    val alternativeGate: String?,
    val severity: DisruptionSeverity,
)

enum class DisruptionSeverity {
    Low,
    Medium,
    High,
}
