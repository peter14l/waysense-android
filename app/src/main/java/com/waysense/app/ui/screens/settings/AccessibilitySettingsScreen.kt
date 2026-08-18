package com.waysense.app.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.waysense.app.ui.components.WaySenseSectionHeader
import com.waysense.app.ui.theme.WayDimens

@Composable
fun AccessibilitySettingsScreen(
    viewModel: AccessibilitySettingsViewModel = viewModel(),
) {
    val profile by viewModel.profile.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(WayDimens.ScreenPadding),
    ) {
        Spacer(modifier = Modifier.height(WayDimens.Space16))
        Text(
            text = "Accessibility",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space24))

        WaySenseSectionHeader(title = "Guidance")
        Spacer(modifier = Modifier.height(WayDimens.Space12))

        SettingToggle(
            label = "Voice guidance",
            checked = profile.voiceGuidance,
            onCheckedChange = { viewModel.updateVoiceGuidance(it) },
        )
        SettingToggle(
            label = "Vibration guidance",
            checked = profile.vibrationGuidance,
            onCheckedChange = { viewModel.updateVibrationGuidance(it) },
        )
        SettingToggle(
            label = "Announce station names",
            checked = profile.announceStationNames,
            onCheckedChange = { viewModel.updateAnnounceStationNames(it) },
        )
        SettingToggle(
            label = "Repeat important alerts",
            checked = profile.repeatImportantAlerts,
            onCheckedChange = { viewModel.updateRepeatImportantAlerts(it) },
        )

        Spacer(modifier = Modifier.height(WayDimens.Space24))
        WaySenseSectionHeader(title = "Display")
        Spacer(modifier = Modifier.height(WayDimens.Space12))

        SettingToggle(
            label = "High contrast",
            checked = profile.highContrast,
            onCheckedChange = { viewModel.updateHighContrast(it) },
        )
        SettingToggle(
            label = "Large text",
            checked = profile.largeText,
            onCheckedChange = { viewModel.updateLargeText(it) },
        )
        SettingToggle(
            label = "Simplified interface",
            checked = profile.simplifiedInterface,
            onCheckedChange = { viewModel.updateSimplifiedInterface(it) },
        )

        Spacer(modifier = Modifier.height(WayDimens.Space24))
        WaySenseSectionHeader(title = "Accessibility profile")
        Spacer(modifier = Modifier.height(WayDimens.Space12))
        Text(
            text = "TalkBack detected",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = "Status will update based on your device settings",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = WayDimens.Space4),
        )
    }
}

@Composable
private fun SettingToggle(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = WayDimens.Space4)
            .semantics {
                toggleableState = if (checked) ToggleableState.On else ToggleableState.Off
                role = androidx.compose.ui.semantics.Role.Switch
                contentDescription = "$label, ${if (checked) "on" else "off"}"
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedTrackColor = MaterialTheme.colorScheme.primary,
            ),
        )
    }
}
