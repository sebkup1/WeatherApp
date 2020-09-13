package com.skupis.weatherapp.json

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentWeatherInfo(
    val WeatherText: String,
    @Json(name = "Temperature") val temperature: Temperature
) : Parcelable

@Parcelize
data class Temperature(@Json(name = "Metric") val metric : Metric) : Parcelable

@Parcelize
data class Metric(val Value : String) : Parcelable