package com.skupis.weatherapp.json

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherForecast (
    val id: String) : Parcelable