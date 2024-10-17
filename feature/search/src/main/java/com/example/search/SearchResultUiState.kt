package com.example.search

import com.example.model.MovieInfo

internal sealed interface SearchResultUiState {
    data object EmptyQuery : SearchResultUiState

    data object LoadFailed : SearchResultUiState

    data class Success(
        val movies: List<MovieInfo> = emptyList(),
    ) : SearchResultUiState {
        fun isEmpty(): Boolean = movies.isEmpty()
    }

}
