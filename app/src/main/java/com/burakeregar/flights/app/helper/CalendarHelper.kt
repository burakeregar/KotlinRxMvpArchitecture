package com.burakeregar.flights.app.helper

import java.text.SimpleDateFormat
import java.util.*

class CalendarHelper {

    fun getNextWeekOfTheDayAsDate(): Pair<String, String> {
        val now = Calendar.getInstance()
        val weekday = now.get(Calendar.DAY_OF_WEEK)
        if (weekday != Calendar.MONDAY) {
            // calculate how much to add
            // the 2 is the difference between Saturday and Monday
            val days = (Calendar.SATURDAY - weekday + 2) % 7
            now.add(Calendar.DAY_OF_YEAR, days)
        }
        val pattern = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val monday = pattern.format(now.time)
        now.add(Calendar.DAY_OF_YEAR, 1) // Get the calendar to next Tuesday
        val tuesday = pattern.format(now.time)
        return Pair(monday, tuesday)
    }
}