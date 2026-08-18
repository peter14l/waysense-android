package com.waysense.app.ui.screens.station

import androidx.compose.foundation.layout.Arrangement
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
import com.waysense.app.data.mock.MockStations
import com.waysense.app.data.model.Station
import com.waysense.app.ui.components.WaySenseAccessibilityBadge
import com.waysense.app.ui.components.WaySenseSectionHeader
import com.waysense.app.ui.theme.WayDimens

class StationDetailsViewModel : androidx.lifecycle.ViewModel() {
    private val _station = kotlinx.coroutines.flow.MutableStateFlow<Station?>(null)
    val station: kotlinx.coroutines.flow.StateFlow<Station?> = _station

    fun loadStation(id: String) {
        _station.value = MockStations.getById(id)
    }
}

@Composable
fun StationDetailsScreen(
    stationId: String,
    onBack: () -> Unit,
    viewModel: StationDetailsViewModel = viewModel(),
) {
    val station by viewModel.station.collectAsStateWithLifecycle()

    LaunchedEffect(stationId) {
        viewModel.loadStation(stationId)
    }

    station?.let { s ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(WayDimens.ScreenPadding),
        ) {
            item {
                Spacer(modifier = Modifier.height(WayDimens.Space16))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack, modifier = Modifier.semantics { contentDescription = "Go back" }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                    Column {
                        Text(
                            text = s.name,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.semantics { heading() },
                        )
                        Text(
                            text = "${s.accessibilityScore} / 5 accessibility score",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(WayDimens.Space24))
                WaySenseSectionHeader(title = "Available features")
                Spacer(modifier = Modifier.height(WayDimens.Space12))
            }

            items(s.features) { feature ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = WayDimens.Space8)
                        .semantics { contentDescription = "${feature.name}, ${if (feature.available) "available" else "unavailable"}" },
                    horizontalArrangement = Arrangement.spacedBy(WayDimens.Space8),
                ) {
                    WaySenseAccessibilityBadge(
                        featureName = feature.name,
                        available = feature.available,
                    )
                }
            }

            if (s.unavailableFeatures.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(WayDimens.Space16))
                    WaySenseSectionHeader(title = "Unavailable")
                    Spacer(modifier = Modifier.height(WayDimens.Space12))
                }
                items(s.unavailableFeatures) { feature ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = WayDimens.Space8)
                            .semantics { contentDescription = "$feature, unavailable" },
                    ) {
                        WaySenseAccessibilityBadge(featureName = feature, available = false)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(WayDimens.Space24))
                Text(
                    text = "Last verified: ${s.lastVerified}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = "Demo data \u2022 Not real-time information",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = WayDimens.Space4),
                )
            }
        }
    }
}
