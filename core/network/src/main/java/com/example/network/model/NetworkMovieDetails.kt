package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovieDetails(
    @SerialName("Title") val title: String = "",
    @SerialName("Year") val year: String = "",
    @SerialName("Rated") val rated: String = "",
    @SerialName("Released") val released: String = "",
    @SerialName("Runtime") val runtime: String = "",
    @SerialName("Genre") val genre: String = "",
    @SerialName("Director") val director: String = "",
    @SerialName("Writer") val writer: String = "",
    @SerialName("Actors") val actors: String = "",
    @SerialName("Plot") val plot: String = "",
    @SerialName("Language") val language: String = "",
    @SerialName("Country") val country: String = "",
    @SerialName("Awards") val awards: String = "",
    @SerialName("Poster") val poster: String = "",
    @SerialName("Ratings") val ratings: List<Rating> = emptyList(),
    @SerialName("Metascore") val metascore: String = "",
    @SerialName("imdbRating") val imdbRating: String = "",
    @SerialName("imdbVotes") val imdbVotes: String = "",
    @SerialName("imdbID") val imdbID: String = "",
    @SerialName("Type") val type: String = "",
    @SerialName("DVD") val dvd: String = "N/A",
    @SerialName("BoxOffice") val boxOffice: String = "N/A",
    @SerialName("Production") val production: String = "N/A",
    @SerialName("Website") val website: String = "N/A",
    @SerialName("Response") val response: String = "False"
)

@Serializable
data class Rating(
    @SerialName("Source") val source: String = "",
    @SerialName("Value") val value: String = ""
)