package com.burakeregar.flights.app.net.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : SchedulerProvider {

    override val ioScheduler: Scheduler = Schedulers.io()
    override val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
}