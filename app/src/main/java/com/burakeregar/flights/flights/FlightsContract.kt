package com.burakeregar.flights.flights

import com.burakeregar.flights.app.domain.model.FlightDetails

interface FlightsContract {
    interface View {
        fun setFlights(flights: List<FlightDetails>)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter {
        fun getFlights(
            origin: String,
            destination: String,
            adults: Int
        )

        fun onDestroy()
    }
}