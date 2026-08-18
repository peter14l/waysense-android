package com.waysense.app.data.repository

import com.waysense.app.data.mock.MockStations
import com.waysense.app.data.model.Station
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StationRepository {
    fun getAllStations(): Flow<List<Station>> = flow {
        emit(MockStations.stations)
    }

    fun getStationById(id: String): Flow<Station?> = flow {
        emit(MockStations.getById(id))
    }

    fun getNearbyStations(): Flow<List<Station>> = flow {
        emit(MockStations.getNearby())
    }

    fun searchStations(query: String): Flow<List<Station>> = flow {
        val results = MockStations.stations.filter {
            it.name.contains(query, ignoreCase = true)
        }
        emit(results)
    }
}
