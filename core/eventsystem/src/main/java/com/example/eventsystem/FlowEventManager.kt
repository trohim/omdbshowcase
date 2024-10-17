package com.example.eventsystem

import com.example.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FlowEventManager @Inject constructor() : EventBus {
    private val flow = MutableSharedFlow<Event>(replay = 1)
    override fun events(): Flow<Event> = flow

    override suspend fun dispatchEvent(event: Event) {
        flow.emit(event)
    }
}