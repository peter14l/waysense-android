package com.waysense.app.ui.screens.search

import androidx.lifecycle.ViewModel
import com.waysense.app.data.mock.MockStations
import com.waysense.app.data.model.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SearchState(
    val query: String = "",
    val results: List<Station> = emptyList(),
    val hasSearched: Boolean = false,
)

class SearchViewModel : ViewModel() {
    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    fun onQueryChange(query: String) {
        _state.value = _state.value.copy(query = query)
        if (query.length >= 2) {
            val results = MockStations.stations.filter {
                it.name.contains(query, ignoreCase = true)
            }
            _state.value = _state.value.copy(results = results, hasSearched = true)
        } else {
            _state.value = _state.value.copy(results = emptyList(), hasSearched = false)
        }
    }

    fun clearQuery() {
        _state.value = SearchState()
    }
}
