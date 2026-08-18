package com.waysense.app.ui.screens.journey

import androidx.lifecycle.ViewModel
import com.waysense.app.data.mock.MockDisruptions
import com.waysense.app.data.mock.MockNavigationSteps
import com.waysense.app.data.model.Disruption
import com.waysense.app.data.model.NavigationStep
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ActiveJourneyState(
    val steps: List<NavigationStep> = MockNavigationSteps.activeJourneySteps,
    val currentStepIndex: Int = 0,
    val isPaused: Boolean = false,
    val showDisruption: Boolean = false,
    val disruption: Disruption = MockDisruptions.disruption,
    val isUsingAlternative: Boolean = false,
    val isComplete: Boolean = false,
    val progressPercent: Float = 0f,
) {
    val currentStep: NavigationStep?
        get() = steps.getOrNull(currentStepIndex)

    val nextStep: NavigationStep?
        get() = steps.getOrNull(currentStepIndex + 1)
}

class ActiveJourneyViewModel : ViewModel() {
    private val _state = MutableStateFlow(ActiveJourneyState())
    val state: StateFlow<ActiveJourneyState> = _state.asStateFlow()

    fun togglePause() {
        _state.value = _state.value.copy(isPaused = !_state.value.isPaused)
    }

    fun advanceStep() {
        val state = _state.value
        if (state.isPaused || state.isComplete) return

        val nextIndex = state.currentStepIndex + 1
        if (nextIndex >= state.steps.size) {
            _state.value = state.copy(isComplete = true, progressPercent = 1f)
        } else {
            val progress = nextIndex.toFloat() / state.steps.size
            _state.value = state.copy(currentStepIndex = nextIndex, progressPercent = progress)
        }
    }

    fun simulateDisruption() {
        _state.value = _state.value.copy(showDisruption = true)
    }

    fun useAlternativeRoute() {
        _state.value = _state.value.copy(
            steps = MockNavigationSteps.alternativeSteps,
            currentStepIndex = 0,
            showDisruption = false,
            isUsingAlternative = true,
            progressPercent = 0f,
        )
    }

    fun dismissDisruption() {
        _state.value = _state.value.copy(showDisruption = false)
    }
}
