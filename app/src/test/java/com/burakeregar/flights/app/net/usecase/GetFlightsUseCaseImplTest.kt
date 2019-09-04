package com.burakeregar.flights.app.net.usecase

import com.burakeregar.flights.app.domain.model.FlightsResponse
import com.burakeregar.flights.app.helper.ImmediateSchedulerProvider
import com.burakeregar.flights.app.helper.TestSchedulerProvider
import com.burakeregar.flights.app.net.repository.FlightsRepository
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.Headers
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.Spy
import retrofit2.Response

class GetFlightsUseCaseImplTest {

    private companion object {
        private const val COUNTRY = "UK"
        private const val CURRENCY = "GBP"
        private const val LOCALE = "en-GB"
        private const val SOME_ORIGIN = "SOME_ORIGIN"
        private const val SOME_DESTINATION = "SOME_DESTINATION"
        private const val SOME_OUTBOUND_DATE = "SOME_OUTBOUND_DATE"
        private const val SOME_INBOUND_DATE = "SOME_INBOUND_DATE"
        private const val SOME_ADULT_COUNT = 1
        private const val EXPECTED_HEADER_KEY = "Location"
        private const val EXPECTED_HEADER_VALUE = "https://api.skyscanner.com/test"
        private val mockResponse = (mock(Response::class.java) as Response<Unit>).apply {
            val headers = mock(Headers::class.java)
            given(headers()).willReturn(headers)
            given(headers[EXPECTED_HEADER_KEY]).willReturn(EXPECTED_HEADER_VALUE)
        }
        private val pollResultsResponse = mock(FlightsResponse::class.java)
    }

    @Mock
    private lateinit var mockRepository: FlightsRepository

    @Mock
    private lateinit var mockCallback: GetFlightsUseCase.Callback

    @Spy
    private val immediateSchedulerProvider = ImmediateSchedulerProvider()

    private val testSchedulerProvider = TestSchedulerProvider()

    private lateinit var subject: GetFlightsUseCase

    @Before
    fun setUp() {
        initMocks(this)
        subject = GetFlightsUseCaseImpl(mockRepository, testSchedulerProvider)
    }

    @Test
    fun `whenExecute givenCreateSessionReturnSuccessfulResponse thenCreateSessionIsCalled and thenPollResultsIsCalled`() {
        givenPollResultsReturnSuccessfulResponse()
        givenCreateSessionReturnSuccessfulResponse(true)
        whenExecute()
        thenCreateSessionIsCalled()
        thenPollResultsIsCalled()
        thenOnUpdatesCompleted()
    }

    @Test
    fun `whenExecute givenCreateSessionReturnUnsuccessfulResponse thenCreateSessionIsCalled and thenOnFlightRequestErrorIsCalled`() {
        givenCreateSessionReturnSuccessfulResponse(false)
        whenExecute()
        thenCreateSessionIsCalled()
        thenOnFlightRequestErrorIsCalled()
    }


    @Test
    fun `whenExecute givenCreateSessionReturnSuccessfulResponse and givenPollResultsReturnUnsuccessfulResponse thenOnFlightRequestErrorIsCalled`() {
        givenPollResultsReturnUnsuccessfulResponse()
        givenCreateSessionReturnSuccessfulResponse(true)
        whenExecute()
        thenCreateSessionIsCalled()
        thenPollResultsIsCalled()
        thenOnFlightRequestErrorIsCalled()
    }

    /*
     * GIVEN
     */

    private fun givenCreateSessionReturnSuccessfulResponse(isSuccessful: Boolean) {
        given(mockResponse.isSuccessful).willReturn(isSuccessful)
        given(
            mockRepository.createSession(
                COUNTRY,
                CURRENCY,
                LOCALE,
                SOME_ORIGIN,
                SOME_DESTINATION,
                SOME_OUTBOUND_DATE,
                SOME_INBOUND_DATE,
                SOME_ADULT_COUNT
            )
        ).willReturn(Single.just(mockResponse))
    }

    private fun givenPollResultsReturnSuccessfulResponse() {
        given(mockRepository.pollResults(EXPECTED_HEADER_VALUE)).willReturn(Flowable.just(pollResultsResponse))
    }

    private fun givenPollResultsReturnUnsuccessfulResponse() {
        given(mockRepository.pollResults(EXPECTED_HEADER_VALUE)).willReturn(Flowable.error(Throwable()))
    }

    /*
     * WHEN
     */

    private fun whenExecute() {
        subject.execute(
            SOME_ORIGIN,
            SOME_DESTINATION,
            SOME_OUTBOUND_DATE,
            SOME_INBOUND_DATE,
            SOME_ADULT_COUNT,
            mockCallback
        )
        testSchedulerProvider.testScheduler.triggerActions()
    }

    /*
     * THEN
     */

    private fun thenCreateSessionIsCalled() {
        then(mockRepository).should().createSession(
            COUNTRY,
            CURRENCY,
            LOCALE,
            SOME_ORIGIN,
            SOME_DESTINATION,
            SOME_OUTBOUND_DATE,
            SOME_INBOUND_DATE,
            SOME_ADULT_COUNT
        )
    }

    private fun thenOnFlightRequestErrorIsCalled() {
        then(mockCallback).should().onFlightRequestError()
    }

    private fun thenPollResultsIsCalled() {
        then(mockRepository).should().pollResults(EXPECTED_HEADER_VALUE)
    }

    private fun thenOnUpdatesCompleted() {
        then(mockCallback).should().onUpdatesCompleted()
    }
}