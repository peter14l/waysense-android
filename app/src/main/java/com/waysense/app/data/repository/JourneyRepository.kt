package com.waysense.app.data.repository

import com.waysense.app.data.mock.MockJourneyHistory
import com.waysense.app.data.mock.MockNavigationSteps
import com.waysense.app.data.model.Journey
import com.waysense.app.data.model.NavigationStep
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JourneyRepository {
    fun getJourneyHistory(): Flow<List<Journey>> = flow {
        emit(MockJourneyHistory.journeyHistory)
    }

    fun getJourneyById(id: String): Flow<Journey?> = flow {
        emit(MockJourneyHistory.getById(id))
    }

    fun getActiveJourneySteps(): Flow<List<NavigationStep>> = flow {
        emit(MockNavigationSteps.activeJourneySteps)
    }

    fun getAlternativeSteps(): Flow<List<NavigationStep>> = flow {
        emit(MockNavigationSteps.alternativeSteps)
    }
}
