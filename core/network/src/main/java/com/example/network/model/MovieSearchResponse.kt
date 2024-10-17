package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResponse(
    @SerialName("Search") val movieInfos: List<NetworkMovieInfo> = listOf(),
    @SerialName("totalResults") val totalResults: String = "0",
    @SerialName("Response") val response: String = "False",
    @SerialName("Error") val error: String? = null
)