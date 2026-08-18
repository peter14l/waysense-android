package com.waysense.app.data.mock

import com.waysense.app.data.model.Disruption
import com.waysense.app.data.model.DisruptionSeverity

object MockDisruptions {

    val disruption = Disruption(
        id = "disruption_1",
        title = "Elevator temporarily unavailable",
        description = "Elevator at Park Street Metro is temporarily unavailable for maintenance.",
        affectedStation = "Park Street Metro",
        alternativeSuggestion = "Use Gate 1 elevator instead",
        alternativeGate = "Gate 1",
        severity = DisruptionSeverity.Medium,
    )
}
