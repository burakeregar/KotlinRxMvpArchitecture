package com.burakeregar.flights.app.net.api

import com.burakeregar.flights.app.net.model.FlightsBackendResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Url
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded

interface FlightsApi {

    @FormUrlEncoded
    @POST("pricing/v1.0/")
    fun createSession(
        @Field("country") country: String,
        @Field("currency") currency: String,
        @Field("locale") locale: String,
        @Field("originPlace") originPlace: String,
        @Field("destinationPlace") destinationPlace: String,
        @Field("outboundDate") outboundDate: String,
        @Field("inboundDate") inboundDate: String,
        @Field("adults") adults: Int,
        @Field("apikey") apiKey: String
    ): Single<Response<Unit>>

    @GET
    fun pollResults(
        @Url url: String,
        @Query("apikey") apiKey: String,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): Single<FlightsBackendResponse>
}