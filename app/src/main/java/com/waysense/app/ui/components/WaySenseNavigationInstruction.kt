package com.waysense.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.waysense.app.ui.theme.WayDimens

@Composable
fun WaySenseNavigationInstruction(
    instruction: String,
    distanceMeters: Int,
    landmark: String?,
    modifier: Modifier = Modifier,
) {
    val description = buildString {
        append(instruction)
        if (landmark != null) append(". Near $landmark")
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.large,
            )
            .padding(WayDimens.ScreenPadding)
            .semantics { contentDescription = description },
        verticalArrangement = Arrangement.spacedBy(WayDimens.Space8),
    ) {
        Text(
            text = instruction,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        if (distanceMeters > 0) {
            Text(
                text = "${distanceMeters}m",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        if (landmark != null) {
            Text(
                text = landmark,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
            )
        }
    }
}
