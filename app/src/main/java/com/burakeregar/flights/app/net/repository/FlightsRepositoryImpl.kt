package com.burakeregar.flights.app.net.repository

import com.burakeregar.flights.BuildConfig
import com.burakeregar.flights.app.domain.model.FlightsResponse
import com.burakeregar.flights.app.net.api.FlightsApi
import com.burakeregar.flights.app.net.mapper.map
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FlightsRepositoryImpl @Inject constructor(private val api: FlightsApi) :
    FlightsRepository {

    private companion object {
        private const val PAGE_SIZE = 10
        private const val UPDATES_COMPLETE = "UpdatesComplete"
    }

    override fun createSession(
        country: String,
        currency: String,
        locale: String,
        originPlace: String,
        destinationPlace: String,
        outboundDate: String,
        inboundDate: String,
        adults: Int
    ) = api.createSession(
        country,
        currency,
        locale,
        originPlace,
        destinationPlace,
        outboundDate,
        inboundDate,
        adults,
        BuildConfig.API_KEY
    )

    override fun pollResults(url: String): Flowable<FlightsResponse> =
        api.pollResults(url, BuildConfig.API_KEY, 0, PAGE_SIZE)
            .repeatWhen {
                it.delay(5, TimeUnit.SECONDS)
            }.takeUntil {
                it.status == UPDATES_COMPLETE
            }.map { it.map() }
}