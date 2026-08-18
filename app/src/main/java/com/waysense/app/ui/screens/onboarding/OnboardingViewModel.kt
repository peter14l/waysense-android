package com.waysense.app.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class OnboardingState(
    val currentStep: Int = 1,
    val navigationStyles: Set<String> = emptySet(),
    val guidanceOptions: Set<String> = setOf("Voice guidance", "Vibration"),
)

class OnboardingViewModel : ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    fun nextStep() {
        _state.value = _state.value.copy(currentStep = (_state.value.currentStep + 1).coerceAtMost(4))
    }

    fun toggleNavigationStyle(style: String) {
        val current = _state.value.navigationStyles.toMutableSet()
        if (current.contains(style)) current.remove(style) else current.add(style)
        _state.value = _state.value.copy(navigationStyles = current)
    }

    fun toggleGuidance(option: String) {
        val current = _state.value.guidanceOptions.toMutableSet()
        if (current.contains(option)) current.remove(option) else current.add(option)
        _state.value = _state.value.copy(guidanceOptions = current)
    }
}
