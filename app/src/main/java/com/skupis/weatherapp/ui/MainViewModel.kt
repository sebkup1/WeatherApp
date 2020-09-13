package com.skupis.weatherapp.ui

import android.widget.AutoCompleteTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skupis.weatherapp.WeatherApi
import com.skupis.weatherapp.json.CurrentWeatherInfo
import kotlinx.coroutines.launch
import timber.log.Timber

enum class WeatherApiStatus { NONE, SEARCHING, ERROR, NOT_FOUND, CITY_FOUND, WEATHER_INFO_FETCHED }

class CityViewModel : ViewModel() {
    private var lastTypedCity: String? = null
    private val _status = MutableLiveData<WeatherApiStatus>()
    val status: LiveData<WeatherApiStatus>
        get() = _status

    var temperature = MutableLiveData<String>()
    var shortDescription = MutableLiveData<String>()

    fun requestHandled() {
        _status.value = WeatherApiStatus.NONE
    }

    fun showWeatherForCity(autoCompleteTextView: AutoCompleteTextView?) {
        val a = autoCompleteTextView?.run {
            val cityName = autoCompleteTextView.text.toString()
            lastTypedCity = cityName
            Timber.d("request for city ${autoCompleteTextView.text}")
            _status.value = WeatherApiStatus.SEARCHING

            viewModelScope.launch {
                _status.value = WeatherApiStatus.SEARCHING
                try {
                    val response = WeatherApi.retrofitService.getCityInfo(cityName = cityName)
                    if (response.isEmpty()) {
                        _status.value = WeatherApiStatus.NOT_FOUND
                        return@launch
                    }
                    _status.value = WeatherApiStatus.CITY_FOUND
                    //Todo: save given cityName to db
                    try {
                        val resp =
                            WeatherApi.retrofitService.getWeatherInfoForCity(cityKey = response[0].Key)
                        parseReceivedData(resp)
                        _status.value = WeatherApiStatus.WEATHER_INFO_FETCHED
                    } catch (e: Exception) {
                        Timber.e(e)
                        _status.value = WeatherApiStatus.ERROR
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                    _status.value = WeatherApiStatus.ERROR
                }
            }
        }
    }

    private fun parseReceivedData(receivedData: List<CurrentWeatherInfo>?) {
        if(receivedData == null) return
        val weatherInfo = receivedData[0]
        shortDescription.value = weatherInfo.WeatherText
        temperature.value = weatherInfo.temperature.metric.Value
    }
}