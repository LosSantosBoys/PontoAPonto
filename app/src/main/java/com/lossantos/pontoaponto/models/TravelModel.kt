package com.lossantos.pontoaponto.models

import java.time.LocalDateTime

enum class TravelState {
    DONE,
    CANCELLED,
    PLANNED
}

enum class TravelType {
    CAR,
    BIKE,
    AIRPLANE,
    BOAT,
    OTHER
}

data class TravelModel(
    val address: String,
    val price: Double,
    val state: TravelState,
    val dateTime: LocalDateTime,
    val type: TravelType
)