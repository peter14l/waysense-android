package com.waysense.app.data.repository

import com.waysense.app.data.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsRepository {
    private val _profile = MutableStateFlow(UserProfile())
    val profile: StateFlow<UserProfile> = _profile.asStateFlow()

    fun updateProfile(update: (UserProfile) -> UserProfile) {
        _profile.value = update(_profile.value)
    }
}
