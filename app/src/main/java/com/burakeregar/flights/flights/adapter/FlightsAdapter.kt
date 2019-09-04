package com.burakeregar.flights.flights.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burakeregar.flights.R
import com.burakeregar.flights.app.domain.model.FlightDetails

class FlightsAdapter : RecyclerView.Adapter<FlightsViewHolder>() {

    private val flightsList = arrayListOf<FlightDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FlightsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_flight_itinerary, parent, false)
        )

    override fun onBindViewHolder(holder: FlightsViewHolder, position: Int) {
        holder.bind(flightsList[position])
    }

    override fun getItemCount() = flightsList.size

    fun setFlights(flights: List<FlightDetails>) {
        flightsList.clear()
        flightsList.addAll(flights)
        notifyDataSetChanged()
    }
}