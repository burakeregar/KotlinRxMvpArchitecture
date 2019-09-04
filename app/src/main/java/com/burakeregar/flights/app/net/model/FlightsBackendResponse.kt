package com.burakeregar.flights.app.net.model

import com.google.gson.annotations.SerializedName

data class FlightsBackendResponse(
    @SerializedName("Agents")
    val agents: List<Agent>,
    @SerializedName("Carriers")
    val carriers: List<Carrier>,
    @SerializedName("Currencies")
    val currencies: List<Currency>,
    @SerializedName("Itineraries")
    val itineraries: List<Itinerary>,
    @SerializedName("Legs")
    val legs: List<Leg>,
    @SerializedName("Places")
    val places: List<Place>,
    @SerializedName("Query")
    val query: Query,
    @SerializedName("Segments")
    val segments: List<Segment>,
    @SerializedName("ServiceQuery")
    val serviceQuery: ServiceQuery,
    @SerializedName("SessionKey")
    val sessionKey: String,
    @SerializedName("Status")
    val status: String
) {
    data class Itinerary(
        @SerializedName("BookingDetailsLink")
        val bookingDetailsLink: BookingDetailsLink,
        @SerializedName("InboundLegId")
        val inboundLegId: String,
        @SerializedName("OutboundLegId")
        val outboundLegId: String,
        @SerializedName("PricingOptions")
        val pricingOptions: List<PricingOption>
    ) {
        data class BookingDetailsLink(
            @SerializedName("Body")
            val body: String,
            @SerializedName("Method")
            val method: String,
            @SerializedName("Uri")
            val uri: String
        )

        data class PricingOption(
            @SerializedName("Agents")
            val agents: List<Int>,
            @SerializedName("DeeplinkUrl")
            val deeplinkUrl: String,
            @SerializedName("Price")
            val price: Double,
            @SerializedName("QuoteAgeInMinutes")
            val quoteAgeInMinutes: Int
        )
    }

    data class Carrier(
        @SerializedName("Code")
        val code: String,
        @SerializedName("DisplayCode")
        val displayCode: String,
        @SerializedName("Id")
        val id: Int,
        @SerializedName("ImageUrl")
        val imageUrl: String,
        @SerializedName("Name")
        val name: String
    )

    data class Agent(
        @SerializedName("Id")
        val id: Int,
        @SerializedName("ImageUrl")
        val imageUrl: String,
        @SerializedName("Name")
        val name: String,
        @SerializedName("OptimisedForMobile")
        val optimisedForMobile: Boolean,
        @SerializedName("Status")
        val status: String,
        @SerializedName("Type")
        val type: String
    )

    data class Currency(
        @SerializedName("Code")
        val code: String,
        @SerializedName("DecimalDigits")
        val decimalDigits: Int,
        @SerializedName("DecimalSeparator")
        val decimalSeparator: String,
        @SerializedName("RoundingCoefficient")
        val roundingCoefficient: Int,
        @SerializedName("SpaceBetweenAmountAndSymbol")
        val spaceBetweenAmountAndSymbol: Boolean,
        @SerializedName("Symbol")
        val symbol: String,
        @SerializedName("SymbolOnLeft")
        val symbolOnLeft: Boolean,
        @SerializedName("ThousandsSeparator")
        val thousandsSeparator: String
    )

    data class ServiceQuery(
        @SerializedName("DateTime")
        val dateTime: String
    )

    data class Query(
        @SerializedName("Adults")
        val adults: Int,
        @SerializedName("CabinClass")
        val cabinClass: String,
        @SerializedName("Children")
        val children: Int,
        @SerializedName("Country")
        val country: String,
        @SerializedName("Currency")
        val currency: String,
        @SerializedName("DestinationPlace")
        val destinationPlace: String,
        @SerializedName("GroupPricing")
        val groupPricing: Boolean,
        @SerializedName("InboundDate")
        val inboundDate: String,
        @SerializedName("Infants")
        val infants: Int,
        @SerializedName("Locale")
        val locale: String,
        @SerializedName("LocationSchema")
        val locationSchema: String,
        @SerializedName("OriginPlace")
        val originPlace: String,
        @SerializedName("OutboundDate")
        val outboundDate: String
    )

    data class Leg(
        @SerializedName("Arrival")
        val arrival: String,
        @SerializedName("Carriers")
        val carriers: List<Int>,
        @SerializedName("Departure")
        val departure: String,
        @SerializedName("DestinationStation")
        val destinationStation: Int,
        @SerializedName("Directionality")
        val directionality: String,
        @SerializedName("Duration")
        val duration: Int,
        @SerializedName("FlightNumbers")
        val flightNumbers: List<FlightNumber>,
        @SerializedName("Id")
        val id: String,
        @SerializedName("JourneyMode")
        val journeyMode: String,
        @SerializedName("OperatingCarriers")
        val operatingCarriers: List<Int>,
        @SerializedName("OriginStation")
        val originStation: Int,
        @SerializedName("SegmentIds")
        val segmentIds: List<Int>,
        @SerializedName("Stops")
        val stops: List<Int>?
    ) {
        data class FlightNumber(
            @SerializedName("CarrierId")
            val carrierId: Int,
            @SerializedName("FlightNumber")
            val flightNumber: String
        )
    }

    data class Segment(
        @SerializedName("ArrivalDateTime")
        val arrivalDateTime: String,
        @SerializedName("Carrier")
        val carrier: Int,
        @SerializedName("DepartureDateTime")
        val departureDateTime: String,
        @SerializedName("DestinationStation")
        val destinationStation: Int,
        @SerializedName("Directionality")
        val directionality: String,
        @SerializedName("Duration")
        val duration: Int,
        @SerializedName("FlightNumber")
        val flightNumber: String,
        @SerializedName("Id")
        val id: Int,
        @SerializedName("JourneyMode")
        val journeyMode: String,
        @SerializedName("OperatingCarrier")
        val operatingCarrier: Int,
        @SerializedName("OriginStation")
        val originStation: Int
    )

    data class Place(
        @SerializedName("Code")
        val code: String,
        @SerializedName("Id")
        val id: Int,
        @SerializedName("Name")
        val name: String,
        @SerializedName("ParentId")
        val parentId: Int,
        @SerializedName("Type")
        val type: String
    )
}