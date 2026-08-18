package com.waysense.app.ui.screens.journey

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import com.waysense.app.data.model.Disruption
import com.waysense.app.ui.components.WaySensePrimaryButton
import com.waysense.app.ui.components.WaySenseSecondaryButton
import com.waysense.app.ui.theme.WayDimens

@Composable
fun DisruptionAlertSheet(
    disruption: Disruption,
    onUseAlternative: () -> Unit,
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(WayDimens.ScreenPadding)
            .semantics { contentDescription = "Disruption alert. ${disruption.title}. ${disruption.description}" },
    ) {
        Text(
            text = "\u26A0 Journey update",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space12))
        Text(
            text = disruption.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        Text(
            text = disruption.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(WayDimens.Space12))
        Text(
            text = "Your accessible route has changed.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = "Alternative: ${disruption.alternativeSuggestion}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(WayDimens.Space24))
        Arrangement.spacedBy(WayDimens.Space8)
        WaySensePrimaryButton(
            text = "View alternative",
            onClick = onUseAlternative,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        WaySenseSecondaryButton(
            text = "Dismiss",
            onClick = onDismiss,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
