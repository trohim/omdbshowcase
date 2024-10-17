package com.example.data.repository

import com.example.model.MovieDetails
import com.example.model.SearchResult

interface MovieRepository {

    suspend fun searchMoviesByTitle(title: String): SearchResult
    suspend fun getMovieDetailsById(imdbId: String): MovieDetails
}
