package com.burakeregar.flights.app.helper

import com.burakeregar.flights.app.net.scheduler.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ImmediateSchedulerProvider : SchedulerProvider {
    override val ioScheduler: Scheduler = Schedulers.trampoline()
    override val mainScheduler: Scheduler = Schedulers.trampoline()
}