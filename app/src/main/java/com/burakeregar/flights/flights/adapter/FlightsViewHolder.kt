package com.burakeregar.flights.flights.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.burakeregar.flights.R
import com.burakeregar.flights.app.domain.model.FlightDetails
import com.burakeregar.flights.app.extensions.loadFromUrl
import kotlinx.android.synthetic.main.item_flight_itinerary.view.*

class FlightsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: FlightDetails) {
        with(itemView) {
            with(data) {
                date.text = outbound.departureDate + " - " + outbound.arrivalDate
                destinationAndAirline.text =
                    outbound.originStation + "-" + outbound.destinationStation + ", " + outbound.airlineName
                stops.text = if (outbound.stops == 0) context.getString(R.string.direct) else outbound.stops.toString()
                duration.text = outbound.duration
                ratingText.text = rating.toString()
                priceText.text = price
                viaText.text = context.getString(R.string.via, source)

                airlineLogo.loadFromUrl(outbound.airlineLogo)

                inbound?.let {
                    returnDate.text = it.departureDate + " - " + it.arrivalDate
                    returnDestinationAndAirline.text =
                        it.originStation + "-" + it.destinationStation + ", " + it.airlineName
                    returnStops.text = if (it.stops == 0) context.getString(R.string.direct) else it.stops.toString()
                    returnDuration.text = it.duration

                    returnAirlineLogo.loadFromUrl(it.airlineLogo)
                }
            }
        }
    }
}
