package com.waysense.app.ui.screens.search

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.waysense.app.ui.components.WaySenseEmptyState
import com.waysense.app.ui.components.WaySenseSearchBar
import com.waysense.app.ui.theme.WayDimens

@Composable
fun SearchScreen(
    onStationSelected: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: SearchViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
            Text(
                text = "Search",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.semantics { heading() },
            )
        }
        Spacer(modifier = Modifier.height(WayDimens.Space16))
        WaySenseSearchBar(
            query = state.query,
            onQueryChange = { viewModel.onQueryChange(it) },
            onSearch = {},
            onClear = { viewModel.clearQuery() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space16))

        if (state.results.isNotEmpty()) {
            LazyColumn {
                items(state.results) { station ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClickLabel = "Select ${station.name}") {
                                onStationSelected(station.id)
                            }
                            .padding(vertical = WayDimens.Space12)
                            .semantics { contentDescription = "${station.name}, ${station.distanceMeters} metres, ${station.type}" },
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = station.name,
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Text(
                                text = "${station.distanceMeters} m \u2022 ${station.type.name}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                    HorizontalDivider()
                }
            }
        } else if (state.hasSearched) {
            WaySenseEmptyState(
                icon = Icons.Filled.Search,
                title = "No results found",
                subtitle = "Try a different search term",
            )
        }
    }
}
