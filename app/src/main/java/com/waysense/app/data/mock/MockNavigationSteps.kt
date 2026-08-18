package com.waysense.app.data.mock

import com.waysense.app.data.model.NavigationStep

object MockNavigationSteps {

    val activeJourneySteps = listOf(
        NavigationStep(1, "Walk straight for 120 metres", 120, "Park Street", "Flat sidewalk with tactile paving", isCurrentStep = true),
        NavigationStep(2, "Turn left toward Park Street Metro Gate 2", 80, null, "Step-free entrance"),
        NavigationStep(3, "Enter Park Street Metro through Gate 2", 30, "Gate 2", null),
        NavigationStep(4, "Take Blue Line toward Dakshineswar", 180, "Platform 2", "Audio announcements at each stop"),
        NavigationStep(5, "Exit at Esplanade via Exit 3 elevator", 60, "Exit 3", "Elevator available"),
        NavigationStep(6, "Walk to destination", 150, "Esplanade Metro Station", null),
    )

    val alternativeSteps = listOf(
        NavigationStep(1, "Walk straight for 120 metres", 120, "Park Street", "Flat sidewalk with tactile paving", isCurrentStep = true),
        NavigationStep(2, "Turn right toward Gate 1", 100, null, "Elevator available at Gate 1"),
        NavigationStep(3, "Enter Park Street Metro through Gate 1", 30, "Gate 1", null),
        NavigationStep(4, "Take Blue Line toward Dakshineswar", 180, "Platform 2", "Audio announcements at each stop"),
        NavigationStep(5, "Exit at Esplanade via Exit 2 elevator", 60, "Exit 2", "Elevator available"),
        NavigationStep(6, "Walk to destination", 150, "Esplanade Metro Station", null),
    )
}
