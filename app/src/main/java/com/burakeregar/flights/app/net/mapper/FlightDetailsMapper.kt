package com.burakeregar.flights.app.net.mapper

import com.burakeregar.flights.app.domain.model.FlightDetails
import com.burakeregar.flights.app.domain.model.FlightItem
import com.burakeregar.flights.app.domain.model.FlightsResponse
import com.burakeregar.flights.app.net.model.FlightsBackendResponse
import java.text.SimpleDateFormat
import java.util.*

fun FlightsBackendResponse.map(): FlightsResponse {
    val legsMap = legs.map { it.id to it }.toMap() // Have a HashMap to reduce the time complexity to O(n)
    val carriersMap = carriers.map { it.id to it }.toMap() // Trade off between time complexity and space complexity
    val placesMap = places.map { it.id to it }.toMap()
    val agentsMap = agents.map { it.id to it }.toMap()

    val flights = itineraries.map {
        val outBound: FlightItem = legsMap.getValue(it.outboundLegId).map(carriersMap, placesMap)
        val inbound: FlightItem? = legsMap[it.inboundLegId]?.map(carriersMap, placesMap)
        val price = "${currencies.first().symbol}${it.pricingOptions.first().price}"
        val source = agentsMap.getValue(it.pricingOptions.first().agents.first()).name
        FlightDetails(outBound, inbound, 8.0, price, source)
        //TODO: Map rating to the related icon
    }
    return FlightsResponse(flights)
}

fun FlightsBackendResponse.Leg.map(
    carriersMap: Map<Int, FlightsBackendResponse.Carrier>,
    places: Map<Int, FlightsBackendResponse.Place>
): FlightItem {

    fun fullDateToHourAndMinutes(date: Date): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
    }

    fun minutesToHoursAndMinutes(duration: Int): String {
        val durationAsDate = SimpleDateFormat("mm", Locale.getDefault()).parse(duration.toString())
        return SimpleDateFormat("HH'h' mm'm'", Locale.getDefault()).format(durationAsDate)
    }

    val fullDateFormat = SimpleDateFormat("dd-MM-yyyy'T'hh:mm:ss", Locale.getDefault())
    val airlineLogo = carriersMap.getValue(carriers.first()).imageUrl
    val departureDate = fullDateToHourAndMinutes(fullDateFormat.parse(departure))
    val arrivalDate = fullDateToHourAndMinutes(fullDateFormat.parse(arrival))
    val originStation = places.getValue(originStation).code
    val destinationStation = places.getValue(destinationStation).code
    val airlineName = carriersMap.getValue(carriers.first()).name
    val stops = stops?.size ?: 0
    val duration = minutesToHoursAndMinutes(duration)

    return FlightItem(
        airlineLogo,
        departureDate,
        arrivalDate,
        originStation,
        destinationStation,
        airlineName,
        stops,
        duration
    )
}