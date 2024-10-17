package com.example.details

import com.example.model.MovieDetails

sealed interface DetailsUiState {
    data object Empty : DetailsUiState

    data object LoadFailed : DetailsUiState

    data class Success(
        val movieDetails: MovieDetails
    ) : DetailsUiState

}
