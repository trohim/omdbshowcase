package com.example.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.MovieRepository
import com.example.eventsystem.EventBus
import com.example.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    eventBus: EventBus, private val movieRepository: MovieRepository
) : ViewModel() {

    val uiState: StateFlow<DetailsUiState> =
        eventBus.events().filter { it is Event.ItemSelected }.distinctUntilChanged().flatMapLatest {
            println("ITEM SELECTED $it")
            val info = it as Event.ItemSelected
            flowOf(DetailsUiState.Success(movieRepository.getMovieDetailsById(info.item.id)))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = DetailsUiState.Empty,
        )

}