package com.burakeregar.flights.flights.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.burakeregar.flights.R
import com.burakeregar.flights.app.domain.model.FlightDetails
import com.burakeregar.flights.flights.FlightsContract
import com.burakeregar.flights.flights.adapter.FlightsAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class FlightsActivity : DaggerAppCompatActivity(), FlightsContract.View {

    @Inject
    internal lateinit var presenter: FlightsContract.Presenter

    @Inject
    internal lateinit var adapter: FlightsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        presenter.getFlights("EDI-sky", "LOND-sky", 1)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun setFlights(flights: List<FlightDetails>) {
        adapter.setFlights(flights)
    }

    override fun hideProgressBar() {
        progressBar.visibility = GONE
    }

    override fun showProgressBar() {
        progressBar.visibility = VISIBLE
    }

    private fun setupRecyclerView() {
        flightsRecyclerView.adapter = adapter
    }
}
