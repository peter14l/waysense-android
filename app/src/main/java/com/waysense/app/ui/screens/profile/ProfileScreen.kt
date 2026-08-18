package com.waysense.app.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.waysense.app.ui.components.WaySenseSectionHeader
import com.waysense.app.ui.theme.WayDimens

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(WayDimens.ScreenPadding),
    ) {
        Spacer(modifier = Modifier.height(WayDimens.Space16))
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space24))

        Column(
            modifier = Modifier.semantics { contentDescription = "User profile: Alex" },
        ) {
            Text(
                text = "Alex",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = "Accessibility preferences: Voice + Vibration",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        Spacer(modifier = Modifier.height(WayDimens.Space24))
        WaySenseSectionHeader(title = "Saved places")
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        Text(
            text = "Home, College, Work",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(WayDimens.Space24))
        WaySenseSectionHeader(title = "Journey history")
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        Text(
            text = "3 recent journeys",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(WayDimens.Space24))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(WayDimens.Space16))

        WaySenseSectionHeader(title = "About WaySense")
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        Text(
            text = "WaySense is an accessibility-first public transport navigation prototype designed for visually impaired commuters.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(WayDimens.Space16))

        WaySenseSectionHeader(title = "Privacy")
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        Text(
            text = "No data is collected. This is a demo application.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(WayDimens.Space16))

        WaySenseSectionHeader(title = "Version")
        Spacer(modifier = Modifier.height(WayDimens.Space8))
        Text(
            text = "WaySense v0.1.0 (Academic Prototype)",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
