package com.skupis.weatherapp.log

import timber.log.Timber

class DebugTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val tagWithPrefix = "WeatherApp | $tag"
        super.log(priority, tagWithPrefix, message, t)
    }
}