package com.burakeregar.flights.app.net.repository

import com.burakeregar.flights.app.domain.model.FlightsResponse
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

interface FlightsRepository {
    fun createSession(
        country: String,
        currency: String,
        locale: String,
        originPlace: String,
        destinationPlace: String,
        outboundDate: String,
        inboundDate: String,
        adults: Int
    ): Single<Response<Unit>>

    fun pollResults(url: String): Flowable<FlightsResponse>
}