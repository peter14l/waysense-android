package com.waysense.app.data.repository

import com.waysense.app.data.mock.MockRoutes
import com.waysense.app.data.model.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RouteRepository {
    fun getRoutesToStation(destinationId: String): Flow<List<Route>> = flow {
        emit(MockRoutes.getRoutesToStation(destinationId))
    }
}
