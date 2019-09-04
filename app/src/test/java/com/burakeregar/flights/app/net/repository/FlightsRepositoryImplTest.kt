package com.burakeregar.flights.app.net.repository

import com.burakeregar.flights.BuildConfig
import com.burakeregar.flights.app.domain.model.FlightsResponse
import com.burakeregar.flights.app.net.api.FlightsApi
import com.burakeregar.flights.app.net.mapper.map
import com.burakeregar.flights.app.net.model.FlightsBackendResponse
import io.mockk.every
import io.mockk.mockkStatic
import io.reactivex.Single
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.atLeastOnce
import org.mockito.MockitoAnnotations.initMocks

class FlightsRepositoryImplTest {

    private companion object {
        private const val SOME_URL = "SOME_URL"
        private const val SOME_COUNTRY = "SOME_COUNTRY"
        private const val SOME_CURRENCY = "SOME_CURRENCY"
        private const val SOME_LOCALE = "SOME_LOCALE"
        private const val SOME_ORIGIN = "SOME_ORIGIN"
        private const val SOME_DESTINATION = "SOME_DESTINATION"
        private const val SOME_OUTBOUND_DATE = "SOME_OUTBOUND_DATE"
        private const val SOME_INBOUND_DATE = "SOME_INBOUND_DATE"
        private const val SOME_ADULT_COUNT = 1
        private const val PAGE_SIZE = 10
        private const val UPDATES_COMPLETE = "UpdatesComplete"
    }

    @Mock
    private lateinit var api: FlightsApi

    @Mock
    private lateinit var mockFlightResponse: FlightsBackendResponse

    @Mock
    private lateinit var mockFlightDetails: FlightsResponse

    private lateinit var subject: FlightsRepository

    private lateinit var pollResult: TestSubscriber<FlightsResponse>


    @Before
    fun setUp() {
        initMocks(this)
        subject = FlightsRepositoryImpl(api)
    }

    @Test
    fun `whenPollResults givenPollResultsSuccessful givenUrl thenApiPollResultsReturnsExpectedResponse`() {
        givenFlightResponseMapper()
        givenPollResultsSuccessful()
        whenPollResults()
        thenResultIsPollResult()
        thenMapperIsCalled()
    }

    @Test
    fun `whenCreateSession thenApiCreateSessionIsCalled`() {
        whenCreateSession()
        thenApiCreateSessionIsCalled()
    }

    /*
     * GIVEN
     */

    private fun givenFlightResponseMapper() {
        mockkStatic("com.burakeregar.flights.app.net.mapper.FlightDetailsMapperKt")
        every {
            mockFlightResponse.map()
        } returns mockFlightDetails
    }

    private fun givenPollResultsSuccessful() {
        given(mockFlightResponse.status).willReturn(UPDATES_COMPLETE)
        given(api.pollResults(SOME_URL, BuildConfig.API_KEY, 0, PAGE_SIZE)).willReturn(Single.just(mockFlightResponse))
    }

    /*
     * WHEN
     */

    private fun whenCreateSession() {
        subject.createSession(
            SOME_COUNTRY,
            SOME_CURRENCY,
            SOME_LOCALE,
            SOME_ORIGIN,
            SOME_DESTINATION,
            SOME_OUTBOUND_DATE,
            SOME_INBOUND_DATE,
            SOME_ADULT_COUNT
        )
    }

    private fun whenPollResults() {
        pollResult = subject.pollResults(SOME_URL).test()
    }

    /*
    * THEN
    */

    private fun thenApiCreateSessionIsCalled() {
        then(api).should()
            .createSession(
                SOME_COUNTRY,
                SOME_CURRENCY,
                SOME_LOCALE,
                SOME_ORIGIN,
                SOME_DESTINATION,
                SOME_OUTBOUND_DATE,
                SOME_INBOUND_DATE,
                SOME_ADULT_COUNT,
                BuildConfig.API_KEY
            )
    }

    private fun thenResultIsPollResult() {
        pollResult.assertResult(mockFlightDetails)
    }

    private fun thenMapperIsCalled() {
        then(mockFlightResponse).should(atLeastOnce()).map()
    }
}