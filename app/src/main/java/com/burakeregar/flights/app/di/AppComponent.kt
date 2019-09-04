package com.burakeregar.flights.app.di

import com.burakeregar.flights.FlightsApp
import com.burakeregar.flights.app.di.scopes.PerApplication
import com.burakeregar.flights.flights.di.FlightsBindings
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        FlightsBindings::class
    ]
)
interface AppComponent : AndroidInjector<FlightsApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<FlightsApp>()
}