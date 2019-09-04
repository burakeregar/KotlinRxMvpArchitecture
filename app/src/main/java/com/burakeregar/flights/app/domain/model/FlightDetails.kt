package com.burakeregar.flights.app.domain.model

data class FlightDetails(
    val outbound: FlightItem,
    val inbound: FlightItem?,
    val rating: Double,
    val price: String,
    val source: String
)

data class FlightItem(
    val airlineLogo: String,
    val departureDate: String,
    val arrivalDate: String,
    val originStation: String,
    val destinationStation: String,
    val airlineName: String,
    val stops: Int,
    val duration: String
)