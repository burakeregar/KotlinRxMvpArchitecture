package com.burakeregar.flights

import com.burakeregar.flights.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class FlightsApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<FlightsApp> =
        DaggerAppComponent.builder().create(this)
}
