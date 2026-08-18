package com.waysense.app.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.waysense.app.ui.components.WaySenseSearchBar
import com.waysense.app.ui.components.WaySenseSectionHeader
import com.waysense.app.ui.components.WaySenseStationCard
import com.waysense.app.ui.theme.WayDimens

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    onStationClick: (String) -> Unit,
    onStationDetailsClick: (String) -> Unit,
    viewModel: HomeViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(WayDimens.ScreenPadding),
    ) {
        Spacer(modifier = Modifier.height(WayDimens.Space16))
        Text(
            text = state.greeting,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space24))
        Text(
            text = "Where are you going?",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space12))
        WaySenseSearchBar(
            query = "",
            onQueryChange = {},
            onSearch = onSearchClick,
            onClear = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onSearchClick() }
                .semantics { contentDescription = "Search destination. Tap to open search." },
            placeholder = "Search destination",
        )
        Spacer(modifier = Modifier.height(WayDimens.Space24))
        WaySenseSectionHeader(title = "Quick destinations")
        Spacer(modifier = Modifier.height(WayDimens.Space12))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(WayDimens.Space8),
            verticalArrangement = Arrangement.spacedBy(WayDimens.Space8),
        ) {
            state.quickDestinations.forEach { dest ->
                SuggestionChip(
                    onClick = { onSearchClick() },
                    label = { Text(dest) },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    modifier = Modifier.semantics { contentDescription = "Navigate to $dest" },
                )
            }
        }
        Spacer(modifier = Modifier.height(WayDimens.Space32))
        WaySenseSectionHeader(title = "Accessible nearby")
        Spacer(modifier = Modifier.height(WayDimens.Space12))
        state.nearbyStations.forEach { station ->
            WaySenseStationCard(
                station = station,
                onClick = { onStationDetailsClick(station.id) },
                modifier = Modifier.padding(bottom = WayDimens.Space12),
            )
        }
    }
}
