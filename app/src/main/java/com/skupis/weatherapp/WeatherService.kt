package com.skupis.weatherapp

import com.skupis.weatherapp.data.CityInfo
import com.skupis.weatherapp.data.CurrentWeatherInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://dataservice.accuweather.com//"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WeatherServiceApi {

    @GET("locations/v1/cities/search")
    suspend fun getCityInfo(@Query("apikey") apikey : String = "3CPPMG65JBRTGASrTTGo2AHJTHOOQHkP", @Query("q") cityName: String): List<CityInfo>
}

object WeatherApi {
    val retrofitService : WeatherServiceApi by lazy { retrofit.create(WeatherServiceApi::class.java) }
}