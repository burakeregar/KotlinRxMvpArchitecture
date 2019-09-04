package com.burakeregar.flights.app.helper

import com.burakeregar.flights.app.net.scheduler.SchedulerProvider
import io.reactivex.schedulers.TestScheduler


class TestSchedulerProvider : SchedulerProvider {
    val testScheduler = TestScheduler()
    override val ioScheduler = testScheduler
    override val mainScheduler = testScheduler
}