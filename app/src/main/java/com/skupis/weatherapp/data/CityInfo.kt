package com.skupis.weatherapp.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityInfo (val Key: String) : Parcelable