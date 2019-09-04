package com.burakeregar.flights.app.net.usecase

import com.burakeregar.flights.app.net.repository.FlightsRepository
import com.burakeregar.flights.app.net.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetFlightsUseCaseImpl @Inject constructor(
    private val repository: FlightsRepository,
    private val schedulerProvider: SchedulerProvider
) : GetFlightsUseCase {

    private companion object {
        private const val COUNTRY = "UK"
        private const val CURRENCY = "GBP"
        private const val LOCALE = "en-GB"
        private const val EXPECTED_HEADER_RESPONSE = "Location"
    }

    private val disposables = CompositeDisposable()

    override fun execute(
        origin: String,
        destination: String,
        outBoundDate: String,
        inBoundDate: String,
        adults: Int,
        callback: GetFlightsUseCase.Callback
    ) {
        disposables.add(
            repository.createSession(COUNTRY, CURRENCY, LOCALE, origin, destination, outBoundDate, inBoundDate, adults)
                .subscribeOn(schedulerProvider.ioScheduler)
                .observeOn(schedulerProvider.mainScheduler)
                .subscribe { response ->
                    if (response.isSuccessful)
                        response.headers().get(EXPECTED_HEADER_RESPONSE)?.let { pollResults(it, callback) }
                    else callback.onFlightRequestError()
                }
        )
    }

    override fun cancel() {
        disposables.clear()
    }

    private fun pollResults(url: String, callback: GetFlightsUseCase.Callback) {
        disposables.add(
            repository.pollResults(url)
                .subscribeOn(schedulerProvider.ioScheduler)
                .observeOn(schedulerProvider.mainScheduler)
                .doOnError {
                    callback.onFlightRequestError()
                }
                .doOnComplete {
                    callback.onUpdatesCompleted()
                }
                .subscribe {
                    callback.onGetFlightsSuccess(it.flights)
                }
        )
    }
}