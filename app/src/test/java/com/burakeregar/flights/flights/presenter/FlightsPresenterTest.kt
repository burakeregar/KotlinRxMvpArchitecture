package com.burakeregar.flights.flights.presenter

import com.burakeregar.flights.app.domain.model.FlightDetails
import com.burakeregar.flights.app.helper.CalendarHelper
import com.burakeregar.flights.app.net.usecase.GetFlightsUseCase
import com.burakeregar.flights.flights.FlightsContract
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class FlightsPresenterTest {

    private companion object {
        private const val SOME_ORIGIN = "SOME_ORIGIN"
        private const val SOME_DESTINATION = "SOME_DESTINATION"
        private const val SOME_OUTBOUND_DATE = "SOME_OUTBOUND_DATE"
        private const val SOME_INBOUND_DATE = "SOME_INBOUND_DATE"
        private const val SOME_ADULT_COUNT = 1
    }

    @Mock
    private lateinit var view: FlightsContract.View

    @Mock
    private lateinit var useCase: GetFlightsUseCase

    @Mock
    private lateinit var mockFlights: List<FlightDetails>

    @Mock
    private lateinit var mockCalendarHelper: CalendarHelper

    private lateinit var subject: FlightsPresenter

    @Before
    fun setUp() {
        initMocks(this)
        subject = FlightsPresenter(view, useCase, mockCalendarHelper)
    }

    @Test
    fun `whenOnGetFlightsSuccess givenList thenViewSetFlightsIsCalled`() {
        whenOnGetFlightsSuccess()
        thenViewSetFlightsIsCalled()
    }

    @Test
    fun `whenOnFlightRequestError thenViewHideProgressBar`() {
        whenOnFlightRequestError()
        thenViewHideProgressBar()
    }

    @Test
    fun `whenOnUpdatesCompleted thenViewHideProgressBar`() {
        whenOnUpdatesCompleted()
        thenViewHideProgressBar()
    }

    @Test
    fun `whenOnDestroy thenCancelUseCase`() {
        whenOnDestroy()
        thenCancelUseCase()
    }

    @Test
    fun `whenGetFlights givenRequiredFields thenViewShowProgressBar and thenUseCaseIsExecuted`() {
        givenCalendarHelperReturnsSomeDates()
        whenGetFlights()
        thenViewShowProgressBar()
        thenUseCaseIsExecuted()
    }

    /*
     * GIVEN
     */

    private fun givenCalendarHelperReturnsSomeDates() {
        given(mockCalendarHelper.getNextWeekOfTheDayAsDate()).willReturn(Pair(SOME_OUTBOUND_DATE, SOME_INBOUND_DATE))
    }

    /*
     * WHEN
     */

    private fun whenGetFlights() {
        subject.getFlights(SOME_ORIGIN, SOME_DESTINATION, SOME_ADULT_COUNT)
    }

    private fun whenOnDestroy() {
        subject.onDestroy()
    }

    private fun whenOnUpdatesCompleted() {
        subject.onUpdatesCompleted()
    }

    private fun whenOnFlightRequestError() {
        subject.onFlightRequestError()
    }

    private fun whenOnGetFlightsSuccess() {
        subject.onGetFlightsSuccess(mockFlights)
    }

    /*
     * THEN
     */

    private fun thenCancelUseCase() {
        then(useCase).should().cancel()
    }

    private fun thenViewSetFlightsIsCalled() {
        then(view).should().setFlights(mockFlights)
    }

    private fun thenViewShowProgressBar() {
        then(view).should().showProgressBar()
    }

    private fun thenViewHideProgressBar() {
        then(view).should().hideProgressBar()
    }

    private fun thenUseCaseIsExecuted() {
        then(useCase).should()
            .execute(SOME_ORIGIN, SOME_DESTINATION, SOME_OUTBOUND_DATE, SOME_INBOUND_DATE, SOME_ADULT_COUNT, subject)
    }
}