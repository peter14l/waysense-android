package com.waysense.app.ui.screens.journey

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.waysense.app.ui.components.WaySenseNavigationInstruction
import com.waysense.app.ui.components.WaySensePrimaryButton
import com.waysense.app.ui.components.WaySenseSecondaryButton
import com.waysense.app.ui.components.WaySenseStatusCard
import com.waysense.app.ui.theme.WayDimens

@Composable
fun ActiveJourneyScreen(
    onJourneyComplete: () -> Unit,
    onHelp: () -> Unit,
    onBack: () -> Unit,
    viewModel: ActiveJourneyViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isComplete) {
        if (state.isComplete) onJourneyComplete()
    }

    if (state.showDisruption) {
        DisruptionAlertSheet(
            disruption = state.disruption,
            onUseAlternative = { viewModel.useAlternativeRoute() },
            onDismiss = { viewModel.dismissDisruption() },
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(WayDimens.ScreenPadding),
    ) {
        Spacer(modifier = Modifier.height(WayDimens.Space16))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack, modifier = Modifier.semantics { contentDescription = "Go back" }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Active journey",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.semantics { heading() },
            )
        }
        Spacer(modifier = Modifier.height(WayDimens.Space16))

        LinearProgressIndicator(
            progress = { state.progressPercent },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .semantics { contentDescription = "Journey progress: ${(state.progressPercent * 100).toInt()} percent" },
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(WayDimens.Space24))

        state.currentStep?.let { step ->
            WaySenseNavigationInstruction(
                instruction = step.instruction,
                distanceMeters = step.distanceMeters,
                landmark = step.landmark,
            )
        }

        Spacer(modifier = Modifier.height(WayDimens.Space16))

        state.nextStep?.let { next ->
            Text(
                text = "Next: ${next.instruction}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.semantics { contentDescription = "Next step: ${next.instruction}" },
            )
        }

        Spacer(modifier = Modifier.height(WayDimens.Space24))

        Row(
            horizontalArrangement = Arrangement.spacedBy(WayDimens.Space8),
        ) {
            IconButton(
                onClick = { viewModel.advanceStep() },
                modifier = Modifier
                    .size(56.dp)
                    .semantics { contentDescription = "Repeat instruction" },
            ) {
                Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "Repeat", modifier = Modifier.size(32.dp))
            }
            IconButton(
                onClick = { viewModel.togglePause() },
                modifier = Modifier
                    .size(56.dp)
                    .semantics { contentDescription = if (state.isPaused) "Resume guidance" else "Pause guidance" },
            ) {
                Icon(
                    imageVector = if (state.isPaused) Icons.Filled.PlayArrow else Icons.Filled.Pause,
                    contentDescription = if (state.isPaused) "Resume" else "Pause",
                    modifier = Modifier.size(32.dp),
                )
            }
            IconButton(
                onClick = onHelp,
                modifier = Modifier
                    .size(56.dp)
                    .semantics { contentDescription = "Help" },
            ) {
                Icon(Icons.Filled.Help, contentDescription = "Help", modifier = Modifier.size(32.dp))
            }
        }

        Spacer(modifier = Modifier.height(WayDimens.Space24))
        Text(
            text = "Journey progress",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        WaySenseStatusCard(
            icon = Icons.AutoMirrored.Filled.VolumeUp,
            label = "Walking",
            value = "450 m",
        )
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        WaySenseStatusCard(
            icon = Icons.Filled.Pause,
            label = "Metro",
            value = "18 min",
        )
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        WaySenseStatusCard(
            icon = Icons.Filled.Stop,
            label = "Destination",
            value = "Esplanade",
        )

        Spacer(modifier = Modifier.height(WayDimens.Space24))
        WaySenseSecondaryButton(
            text = "End journey",
            onClick = onJourneyComplete,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(WayDimens.Space8))
        WaySensePrimaryButton(
            text = "Simulate disruption",
            onClick = { viewModel.simulateDisruption() },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
