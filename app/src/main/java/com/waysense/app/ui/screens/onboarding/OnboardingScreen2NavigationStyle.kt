package com.waysense.app.ui.screens.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.waysense.app.ui.components.WaySensePrimaryButton
import com.waysense.app.ui.theme.WayDimens

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OnboardingScreen2NavigationStyle(
    selectedStyles: Set<String>,
    onToggle: (String) -> Unit,
    onNext: () -> Unit,
) {
    val styles = listOf("TalkBack", "Large text", "High contrast", "Voice guidance", "Vibration guidance")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WayDimens.ScreenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(WayDimens.Space64))
        Text(
            text = "How do you navigate?",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        Text(
            text = "Select all that apply",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(WayDimens.Space32))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(WayDimens.Space8),
            verticalArrangement = Arrangement.spacedBy(WayDimens.Space8),
        ) {
            styles.forEach { style ->
                val selected = style in selectedStyles
                Surface(
                    modifier = Modifier
                        .semantics {
                            toggleableState = if (selected) ToggleableState.On else ToggleableState.Off
                            role = androidx.compose.ui.semantics.Role.Checkbox
                            contentDescription = "$style, ${if (selected) "selected" else "not selected"}"
                        }
                        .clickable { onToggle(style) },
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
                        text = style,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        WaySensePrimaryButton(
            text = "Continue",
            onClick = onNext,
        )
    }
}
