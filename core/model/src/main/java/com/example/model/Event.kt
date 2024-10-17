package com.example.model

sealed class Event {
    class ItemSelected(val item: MovieInfo) : Event()
}