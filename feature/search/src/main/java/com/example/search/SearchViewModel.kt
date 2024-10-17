package com.example.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.MovieRepository
import com.example.eventsystem.EventBus
import com.example.model.Event
import com.example.model.MovieInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val eventBus: EventBus,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val searchQueryFlow: StateFlow<String> =
        savedStateHandle.getStateFlow(SEARCH_QUERY_KEY, "")

    val lastSearchQuery: String
        get() = savedStateHandle.get<String>(SEARCH_QUERY_KEY) ?: ""

    val searchResultUiState: StateFlow<SearchResultUiState> = searchQueryFlow
        .debounce(DEBOUNCE_TIMEOUT)
        .map(String::trim)
        .filter(String::isNotBlank)
        .flatMapLatest { query ->
            if (query.length < SEARCH_QUERY_MIN_LENGTH) {
                flowOf(SearchResultUiState.EmptyQuery)
            } else {
                performSearch(query)
            }
        }
        .catch { emit(SearchResultUiState.LoadFailed) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(SUBSCRIPTION_TIMEOUT),
            initialValue = SearchResultUiState.EmptyQuery
        )

    fun search(searchQuery: String) {
        savedStateHandle[SEARCH_QUERY_KEY] = searchQuery
    }

    fun onItemSelected(movie: MovieInfo) {
        viewModelScope.launch {
            eventBus.dispatchEvent(Event.ItemSelected(movie))
        }
    }

    private fun performSearch(query: String): Flow<SearchResultUiState> {
        return flow {
            val searchResult = movieRepository.searchMoviesByTitle(query)
            emit(SearchResultUiState.Success(searchResult.mediaItems))
        }
    }

    companion object {
        private const val SEARCH_QUERY_MIN_LENGTH = 3
        private const val SEARCH_QUERY_KEY = "searchQuery"
        private const val DEBOUNCE_TIMEOUT = 200L
        private const val SUBSCRIPTION_TIMEOUT = 5_000L
    }
}