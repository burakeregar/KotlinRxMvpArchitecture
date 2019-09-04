package com.burakeregar.flights.flights.di

import com.burakeregar.flights.app.di.scopes.PerActivity
import com.burakeregar.flights.flights.view.FlightsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FlightsBindings {
    @PerActivity
    @ContributesAndroidInjector(modules = [FlightsModule::class])
    internal abstract fun contributeJoinClubCardActivityInjector(): FlightsActivity
}