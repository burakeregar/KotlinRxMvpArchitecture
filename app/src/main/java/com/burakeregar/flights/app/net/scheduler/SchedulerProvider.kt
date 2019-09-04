package com.burakeregar.flights.app.net.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {
    val ioScheduler: Scheduler
    val mainScheduler: Scheduler
}