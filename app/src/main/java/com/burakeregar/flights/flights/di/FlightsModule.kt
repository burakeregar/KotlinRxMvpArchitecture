package com.burakeregar.flights.flights.di

import com.burakeregar.flights.app.di.scopes.PerActivity
import com.burakeregar.flights.app.helper.CalendarHelper
import com.burakeregar.flights.app.net.usecase.GetFlightsUseCase
import com.burakeregar.flights.app.net.usecase.GetFlightsUseCaseImpl
import com.burakeregar.flights.flights.FlightsContract
import com.burakeregar.flights.flights.adapter.FlightsAdapter
import com.burakeregar.flights.flights.presenter.FlightsPresenter
import com.burakeregar.flights.flights.view.FlightsActivity
import dagger.Module
import dagger.Provides

@Module
class FlightsModule {
    @Provides
    @PerActivity
    internal fun provideView(view: FlightsActivity): FlightsContract.View = view

    @Provides
    @PerActivity
    internal fun providePresenter(presenter: FlightsPresenter): FlightsContract.Presenter = presenter

    @Provides
    @PerActivity
    internal fun provideGetFlightsUseCase(useCase: GetFlightsUseCaseImpl): GetFlightsUseCase = useCase

    @Provides
    @PerActivity
    internal fun provideFlightsAdapter() = FlightsAdapter()

    @Provides
    @PerActivity
    internal fun provideCalendarHelper() = CalendarHelper()
}