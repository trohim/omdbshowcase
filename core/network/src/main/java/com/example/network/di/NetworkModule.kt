/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.network.di

import com.example.network.BuildConfig
import com.example.network.OmdbNetworkDataSource
import com.example.network.retrofit.RetrofitOmdbNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor {
            val originalRequest = it.request()
            val url = originalRequest.url.newBuilder()
                .addQueryParameter("apikey", BuildConfig.API_KEY) // Add your API key here
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(url)
                .build()
            println("mytag $newRequest")
            it.proceed(newRequest)
        }
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                },
        )

        .build()
}

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModuleBindings {

    @Binds
    fun binds(impl: RetrofitOmdbNetwork): OmdbNetworkDataSource
}
