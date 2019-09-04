package com.burakeregar.flights.app.di

import android.content.Context
import com.burakeregar.flights.FlightsApp
import com.burakeregar.flights.app.di.scopes.PerApplication
import dagger.Provides
import dagger.Module

@Module
class AppModule {
    @Provides
    @PerApplication
    fun provideContext(application: FlightsApp): Context = application
}