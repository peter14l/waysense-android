package com.waysense.app.ui.screens.journeys

import androidx.lifecycle.ViewModel
import com.waysense.app.data.mock.MockJourneyHistory
import com.waysense.app.data.model.Journey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class JourneysState(
    val journeys: List<Journey> = MockJourneyHistory.journeyHistory,
)

class JourneysViewModel : ViewModel() {
    private val _state = MutableStateFlow(JourneysState())
    val state: StateFlow<JourneysState> = _state.asStateFlow()
}
