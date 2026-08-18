package com.waysense.app.ui.screens.route

import androidx.lifecycle.ViewModel
import com.waysense.app.data.mock.MockRoutes
import com.waysense.app.data.mock.MockStations
import com.waysense.app.data.model.Route
import com.waysense.app.data.model.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class RoutePlanningState(
    val station: Station? = null,
    val routes: List<Route> = emptyList(),
    val selectedRouteId: String? = null,
)

class RoutePlanningViewModel : ViewModel() {
    private val _state = MutableStateFlow(RoutePlanningState())
    val state: StateFlow<RoutePlanningState> = _state.asStateFlow()

    fun loadStation(stationId: String) {
        val station = MockStations.getById(stationId)
        val routes = MockRoutes.getRoutesToStation(stationId)
        _state.value = RoutePlanningState(
            station = station,
            routes = routes,
            selectedRouteId = routes.firstOrNull { it.isRecommended }?.id,
        )
    }

    fun selectRoute(routeId: String) {
        _state.value = _state.value.copy(selectedRouteId = routeId)
    }
}
