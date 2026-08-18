package com.waysense.app.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun WaySenseAccessibilityBadge(
    featureName: String,
    available: Boolean,
    modifier: Modifier = Modifier,
) {
    AssistChip(
        onClick = {},
        modifier = modifier.semantics {
            contentDescription = "$featureName, ${if (available) "available" else "unavailable"}"
        },
        label = {
            Text(
                text = featureName,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(end = 4.dp),
            )
        },
        leadingIcon = {
            Icon(
                imageVector = if (available) Icons.Filled.CheckCircle else Icons.Filled.Cancel,
                contentDescription = if (available) "Available" else "Unavailable",
                tint = if (available) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (available) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.errorContainer,
        ),
    )
}
