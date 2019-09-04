package com.burakeregar.flights.app.net.usecase

import com.burakeregar.flights.app.domain.model.FlightDetails

interface GetFlightsUseCase : UseCase {
    fun execute(
        origin: String,
        destination: String,
        outBoundDate: String,
        inBoundDate: String,
        adults: Int,
        callback: Callback
    )

    interface Callback {
        fun onGetFlightsSuccess(flights: List<FlightDetails>)

        fun onFlightRequestError()

        fun onUpdatesCompleted()
    }
}