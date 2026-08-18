package com.waysense.app.ui.screens.help

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.waysense.app.ui.components.WaySensePrimaryButton
import com.waysense.app.ui.components.WaySenseSecondaryButton
import com.waysense.app.ui.theme.WayDimens

@Composable
fun HelpScreen(onBack: () -> Unit) {
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
                text = "Help",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.semantics { heading() },
            )
        }
        Spacer(modifier = Modifier.height(WayDimens.Space24))

        Text(
            text = "Quick actions",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space12))

        val actions = listOf(
            Triple(Icons.Filled.VolumeUp, "Repeat current instruction", "Repeat the current navigation instruction aloud"),
            Triple(Icons.Filled.LocationOn, "Where am I?", "Get your current location and nearest station"),
            Triple(Icons.Filled.DirectionsWalk, "Find nearest accessible exit", "Locate the closest step-free exit"),
            Triple(Icons.Filled.Call, "Contact station assistance", "Call station help desk (demo only)"),
            Triple(Icons.Filled.Close, "End journey", "End the current navigation journey"),
        )

        actions.forEach { (icon, title, description) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = WayDimens.Space8)
                    .semantics { contentDescription = "$title. $description" },
                horizontalArrangement = Arrangement.spacedBy(WayDimens.Space12),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(WayDimens.Space32))
        Text(
            text = "Emergency",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        Text(
            text = "This is a demo application. Emergency features are simulated and do not connect to real services.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(WayDimens.Space16))
        WaySensePrimaryButton(
            text = "Call emergency services (demo)",
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        WaySenseSecondaryButton(
            text = "End journey",
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
