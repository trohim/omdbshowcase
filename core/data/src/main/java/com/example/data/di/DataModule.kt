package com.example.data.di

import com.example.data.repository.MovieRepository
import com.example.data.repository.OnlineMovieRepository
import com.example.data.utils.NetworkMonitor
import com.example.data.utils.NetworkMonitorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModuleBindings {

    @Binds
    internal abstract fun bindsSearchRepository(
        searchContentsRepository: OnlineMovieRepository
    ): MovieRepository

    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: NetworkMonitorImpl,
    ): NetworkMonitor

}

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}
