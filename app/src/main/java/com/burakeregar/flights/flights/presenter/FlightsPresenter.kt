package com.burakeregar.flights.flights.presenter

import com.burakeregar.flights.app.domain.model.FlightDetails
import com.burakeregar.flights.app.helper.CalendarHelper
import com.burakeregar.flights.app.net.usecase.GetFlightsUseCase
import com.burakeregar.flights.flights.FlightsContract
import javax.inject.Inject

class FlightsPresenter @Inject constructor(
    private val view: FlightsContract.View,
    private val useCase: GetFlightsUseCase,
    private val calendarHelper: CalendarHelper
) : FlightsContract.Presenter, GetFlightsUseCase.Callback {

    override fun onGetFlightsSuccess(flights: List<FlightDetails>) {
        view.setFlights(flights)
    }

    override fun onFlightRequestError() {
        view.hideProgressBar()
    }

    override fun onUpdatesCompleted() {
        view.hideProgressBar()
    }

    override fun getFlights(
        origin: String,
        destination: String,
        adults: Int
    ) {
        view.showProgressBar()
        val dateAsPair = calendarHelper.getNextWeekOfTheDayAsDate()
        useCase.execute(origin, destination, dateAsPair.first, dateAsPair.second, adults, this)
    }

    override fun onDestroy() {
        useCase.cancel()
    }
}