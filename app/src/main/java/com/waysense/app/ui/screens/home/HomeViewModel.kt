package com.waysense.app.ui.screens.home

import androidx.lifecycle.ViewModel
import com.waysense.app.data.mock.MockStations
import com.waysense.app.data.model.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeState(
    val greeting: String = "Good evening",
    val nearbyStations: List<Station> = MockStations.getNearby(),
    val quickDestinations: List<String> = listOf("Home", "College", "Work"),
)

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()
}
