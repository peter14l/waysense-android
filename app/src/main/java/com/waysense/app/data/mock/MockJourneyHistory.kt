package com.waysense.app.data.mock

import com.waysense.app.data.model.Journey
import com.waysense.app.data.model.NavigationStep
import com.waysense.app.data.model.TransportMode

object MockJourneyHistory {

    val journeyHistory = listOf(
        Journey(
            id = "journey_today_1",
            originName = "Park Street",
            destinationName = "Esplanade",
            durationMinutes = 24,
            date = "Today",
            isCompleted = false,
            transportMode = TransportMode.Metro,
            steps = listOf(
                NavigationStep(1, "Walk straight for 120 metres", 120, "Park Street", "Flat sidewalk with tactile paving"),
                NavigationStep(2, "Turn left toward Park Street Metro Gate 2", 80, null, "Step-free entrance"),
                NavigationStep(3, "Enter Park Street Metro through Gate 2", 30, "Gate 2", null),
                NavigationStep(4, "Take Blue Line toward Dakshineswar", 180, "Platform 2", "Audio announcements at each stop"),
                NavigationStep(5, "Exit at Esplanade via Exit 3 elevator", 60, "Exit 3", "Elevator available"),
                NavigationStep(6, "Walk to destination", 150, "Esplanade Metro Station", null),
            ),
        ),
        Journey(
            id = "journey_yesterday_1",
            originName = "College",
            destinationName = "Home",
            durationMinutes = 42,
            date = "Yesterday",
            isCompleted = true,
            transportMode = TransportMode.Mixed,
            steps = emptyList(),
        ),
        Journey(
            id = "journey_2days_1",
            originName = "Home",
            destinationName = "Salt Lake Sector V",
            durationMinutes = 35,
            date = "2 days ago",
            isCompleted = true,
            transportMode = TransportMode.Metro,
            steps = emptyList(),
        ),
    )

    fun getById(id: String): Journey? = journeyHistory.find { it.id == id }
}
