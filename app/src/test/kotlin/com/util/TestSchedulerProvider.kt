package com.util

import com.base.util.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/**
 * Created by Burak Eregar on 18.11.2017.
 * burakeregar@gmail.com
 * https://github.com/burakeregar
 */
class TestSchedulerProvider constructor(private val testScheduler: TestScheduler) : SchedulerProvider {
    override fun ui(): Scheduler = testScheduler
    override fun computation(): Scheduler = testScheduler
    override fun io(): Scheduler = testScheduler
}