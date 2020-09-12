package com.skupis.weatherapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentWeatherInfo(
    val WeatherText: String
) : Parcelable