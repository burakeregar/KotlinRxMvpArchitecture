package com.burakeregar.flights.flights.view

import com.burakeregar.flights.app.domain.model.FlightDetails
import com.burakeregar.flights.flights.FlightsContract
import com.burakeregar.flights.flights.adapter.FlightsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.assertj.android.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations.initMocks
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [28])
class FlightsActivityTest {

    @Mock
    private lateinit var mockFlightList: List<FlightDetails>

    private val presenter = mock(FlightsContract.Presenter::class.java)
    private val adapter = mock(FlightsAdapter::class.java)

    private lateinit var activity: FlightsActivity
    private lateinit var controller: ActivityController<FlightsActivity>

    @Before
    fun setUp() {
        controller = Robolectric.buildActivity(FlightsActivity::class.java).apply {
            create()
            activity = get().also {
                it.adapter = adapter
                it.presenter = presenter
            }

        }
        initMocks(this)
    }

    @Test
    fun `whenHideProgressBar thenProgressBarIsNotVisible`() {
        whenHideProgressBar()
        thenProgressBarIsNotVisible()
    }

    @Test
    fun `whenShowProgressBar thenProgressBarIsVisible`() {
        whenShowProgressBar()
        thenProgressBarIsVisible()
    }

    @Test
    fun `whenOnDestroy thenPresenterOnDestroyIsCalled`() {
        whenOnDestroy()
        thenPresenterOnDestroyIsCalled()
    }

    @Test
    fun `whenSetFlights thenAdapterSetList`() {
        whenSetFlights()
        thenAdapterSetList()
    }

    /*
     * WHEN
     */

    private fun whenSetFlights() {
        activity.setFlights(mockFlightList)
    }

    private fun whenOnDestroy() {
        controller.destroy()
    }

    private fun whenShowProgressBar() {
        activity.showProgressBar()
    }

    private fun whenHideProgressBar() {
        activity.hideProgressBar()
    }

    /*
     * THEN
     */

    private fun thenAdapterSetList() {
        then(adapter).should().setFlights(mockFlightList)
    }

    private fun thenProgressBarIsVisible() {
        assertThat(activity.progressBar).isVisible
    }

    private fun thenPresenterOnDestroyIsCalled() {
        then(presenter).should().onDestroy()
    }

    private fun thenProgressBarIsNotVisible() {
        assertThat(activity.progressBar).isNotVisible
    }
}