package com.example.eventsystem.di

import com.example.eventsystem.EventBus
import com.example.eventsystem.FlowEventManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EventSystemModule {

    @Binds
    internal abstract fun bindsEventManager(
        eventManager: FlowEventManager
    ): EventBus

}