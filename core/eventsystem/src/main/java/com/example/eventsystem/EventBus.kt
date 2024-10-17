package com.example.eventsystem

import com.example.model.Event
import kotlinx.coroutines.flow.Flow


interface EventBus {
    fun events(): Flow<Event>
    suspend fun dispatchEvent(event: Event)
}