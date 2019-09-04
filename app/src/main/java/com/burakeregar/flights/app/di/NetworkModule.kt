package com.burakeregar.flights.app.di

import com.burakeregar.flights.BuildConfig
import com.burakeregar.flights.app.di.scopes.PerApplication
import com.burakeregar.flights.app.net.api.FlightsApi
import com.burakeregar.flights.app.net.repository.FlightsRepository
import com.burakeregar.flights.app.net.repository.FlightsRepositoryImpl
import com.burakeregar.flights.app.net.scheduler.AppSchedulerProvider
import com.burakeregar.flights.app.net.scheduler.SchedulerProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    @PerApplication
    internal fun provideScheduler(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    @PerApplication
    internal fun createApi(): FlightsApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(FlightsApi::class.java)
    }

    @Provides
    @PerApplication
    internal fun provideFlightsRepository(repositoryImpl: FlightsRepositoryImpl): FlightsRepository =
        repositoryImpl
}
