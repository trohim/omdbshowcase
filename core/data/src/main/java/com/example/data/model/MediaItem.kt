package com.example.data.model

import com.example.model.MovieDetails
import com.example.model.MovieInfo
import com.example.network.model.NetworkMovieDetails
import com.example.network.model.NetworkMovieInfo

fun NetworkMovieInfo.asExternalModel() =
    MovieInfo(id = imdbID, title = title, releaseYear = year, posterUrl = poster)

fun NetworkMovieDetails.asExternalModel() = MovieDetails(
    id = imdbID,
    title = title,
    releaseYear = released,
    posterUrl = poster,
    plotSummary = plot,
    productionYear = production
)