package com.waysense.app.ui.screens.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.waysense.app.ui.components.WaySensePrimaryButton
import com.waysense.app.ui.theme.WayDimens

@Composable
fun OnboardingScreen3Guidance(
    selectedOptions: Set<String>,
    onToggle: (String) -> Unit,
    onNext: () -> Unit,
) {
    val options = listOf("Voice guidance", "Vibration", "Visual guidance")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WayDimens.ScreenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(WayDimens.Space64))
        Text(
            text = "Choose your guidance",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        Text(
            text = "Select how you'd like to receive navigation updates",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(WayDimens.Space32))
        options.forEach { option ->
            val selected = option in selectedOptions
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = WayDimens.Space4)
                    .semantics {
                        toggleableState = if (selected) ToggleableState.On else ToggleableState.Off
                        role = androidx.compose.ui.semantics.Role.Checkbox
                        contentDescription = "$option, ${if (selected) "selected" else "not selected"}"
                    }
                    .clickable { onToggle(option) },
                shape = MaterialTheme.shapes.medium,
                color = if (selected) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surface,
                border = BorderStroke(
                    2.dp,
                    if (selected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.outlineVariant,
                ),
            ) {
                Text(
                    text = option,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.onSurface,
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        WaySensePrimaryButton(
            text = "Continue",
            onClick = onNext,
        )
    }
}
