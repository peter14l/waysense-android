package com.waysense.app.ui.screens.settings

import androidx.lifecycle.ViewModel
import com.waysense.app.data.model.UserProfile
import com.waysense.app.data.repository.SettingsRepository
import kotlinx.coroutines.flow.StateFlow

class AccessibilitySettingsViewModel : ViewModel() {
    private val repository = SettingsRepository()
    val profile: StateFlow<UserProfile> = repository.profile

    fun updateVoiceGuidance(enabled: Boolean) {
        repository.updateProfile { it.copy(voiceGuidance = enabled) }
    }

    fun updateVibrationGuidance(enabled: Boolean) {
        repository.updateProfile { it.copy(vibrationGuidance = enabled) }
    }

    fun updateHighContrast(enabled: Boolean) {
        repository.updateProfile { it.copy(highContrast = enabled) }
    }

    fun updateLargeText(enabled: Boolean) {
        repository.updateProfile { it.copy(largeText = enabled) }
    }

    fun updateSimplifiedInterface(enabled: Boolean) {
        repository.updateProfile { it.copy(simplifiedInterface = enabled) }
    }

    fun updateAnnounceStationNames(enabled: Boolean) {
        repository.updateProfile { it.copy(announceStationNames = enabled) }
    }

    fun updateRepeatImportantAlerts(enabled: Boolean) {
        repository.updateProfile { it.copy(repeatImportantAlerts = enabled) }
    }
}
