package com.waysense.app.ui.screens.journeys

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
import androidx.compose.material.icons.filled.Route
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.waysense.app.data.mock.MockJourneyHistory
import com.waysense.app.ui.components.WaySenseEmptyState
import com.waysense.app.ui.components.WaySenseSectionHeader
import com.waysense.app.ui.theme.WayDimens

@Composable
fun JourneysScreen(
    onJourneyClick: (String) -> Unit,
    viewModel: JourneysViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WayDimens.ScreenPadding),
    ) {
        Spacer(modifier = Modifier.height(WayDimens.Space16))
        Text(
            text = "Journeys",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.semantics { heading() },
        )
        Spacer(modifier = Modifier.height(WayDimens.Space24))

        if (state.journeys.isEmpty()) {
            WaySenseEmptyState(
                icon = Icons.Filled.Route,
                title = "No journeys yet",
                subtitle = "Your completed journeys will appear here",
            )
        } else {
            val grouped = state.journeys.groupBy { it.date }
            grouped.forEach { (date, journeys) ->
                WaySenseSectionHeader(title = date)
                Spacer(modifier = Modifier.height(WayDimens.Space8))
                journeys.forEach { journey ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClickLabel = "View journey: ${journey.originName} to ${journey.destinationName}") {
                                onJourneyClick(journey.id)
                            }
                            .padding(vertical = WayDimens.Space12)
                            .semantics { contentDescription = "${journey.originName} to ${journey.destinationName}, ${journey.durationMinutes} minutes" },
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${journey.originName} \u2192 ${journey.destinationName}",
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Text(
                                text = "${journey.durationMinutes} min \u2022 ${journey.transportMode.name}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                    HorizontalDivider()
                }
                Spacer(modifier = Modifier.height(WayDimens.Space16))
            }
        }
    }
}
