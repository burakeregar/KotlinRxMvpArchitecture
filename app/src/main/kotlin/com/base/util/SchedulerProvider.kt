package com.base.util

import io.reactivex.Scheduler

/**
 * Created by Burak Eregar on 18.11.2017.
 * burakeregar@gmail.com
 * https://github.com/burakeregar
 */
interface SchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}