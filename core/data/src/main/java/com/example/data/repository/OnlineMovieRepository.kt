package com.example.data.repository

import com.example.data.model.asExternalModel
import com.example.model.MovieDetails
import com.example.model.SearchResult
import com.example.network.OmdbNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class OnlineMovieRepository @Inject constructor(
    private val networkDataSource: OmdbNetworkDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : MovieRepository {
    override suspend fun searchMoviesByTitle(title: String): SearchResult {
        return withContext(ioDispatcher) {
            val result = networkDataSource.search(title)
            SearchResult(result.movieInfos.map { it.asExternalModel() })
        }
    }

    override suspend fun getMovieDetailsById(imdbId: String): MovieDetails {
        return withContext(ioDispatcher) {
            networkDataSource.getDetails(imdbId).asExternalModel()
        }
    }
}