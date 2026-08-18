package com.waysense.app.ui.screens.route

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.waysense.app.ui.components.WaySensePrimaryButton
import com.waysense.app.ui.components.WaySenseRouteCard
import com.waysense.app.ui.components.WaySenseSectionHeader
import com.waysense.app.ui.theme.WayDimens

@Composable
fun RoutePlanningScreen(
    stationId: String,
    onStartJourney: (String) -> Unit,
    onViewMap: (String) -> Unit,
    onStationDetails: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: RoutePlanningViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(stationId) {
        viewModel.loadStation(stationId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WayDimens.ScreenPadding),
    ) {
        Spacer(modifier = Modifier.height(WayDimens.Space16))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack, modifier = Modifier.semantics { contentDescription = "Go back" }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Column {
                Text(
                    text = "Route to ${state.station?.name ?: "Destination"}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.semantics { heading() },
                )
                Text(
                    text = "Origin: Current location",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        Spacer(modifier = Modifier.height(WayDimens.Space24))
        WaySenseSectionHeader(title = "Available routes")
        Spacer(modifier = Modifier.height(WayDimens.Space12))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(state.routes) { route ->
                WaySenseRouteCard(
                    route = route,
                    isSelected = route.id == state.selectedRouteId,
                    onClick = { viewModel.selectRoute(route.id) },
                    modifier = Modifier.padding(bottom = WayDimens.Space12),
                )
            }
        }

        Spacer(modifier = Modifier.height(WayDimens.Space16))

        state.station?.let { station ->
            WaySensePrimaryButton(
                text = "Start journey",
                onClick = { onStartJourney("journey_active_${station.id}") },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
