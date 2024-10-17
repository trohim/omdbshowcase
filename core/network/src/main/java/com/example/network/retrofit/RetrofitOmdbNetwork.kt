package com.example.network.retrofit

import com.example.network.BuildConfig
import com.example.network.OmdbNetworkDataSource
import com.example.network.model.MovieSearchResponse
import com.example.network.model.NetworkMovieDetails
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = BuildConfig.BASE_URL

private interface RetrofitOmdbNetworkApi {
    @GET(value = "/")
    suspend fun search(
        @Query("s") title: String,
    ): MovieSearchResponse

    @GET(value = "/")
    suspend fun getDetails(@Query("i") imdbId: String): NetworkMovieDetails
}


@Singleton
internal class RetrofitOmdbNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : OmdbNetworkDataSource {

    private val networkApi =
        Retrofit.Builder().baseUrl(BASE_URL).callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            ).build().create(RetrofitOmdbNetworkApi::class.java)

    override suspend fun search(title: String): MovieSearchResponse = networkApi.search(title)
    override suspend fun getDetails(imdbId: String): NetworkMovieDetails =
        networkApi.getDetails(imdbId)
}
