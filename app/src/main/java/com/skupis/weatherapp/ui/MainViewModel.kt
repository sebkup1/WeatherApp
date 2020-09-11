package com.skupis.weatherapp.ui

import android.widget.AutoCompleteTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skupis.weatherapp.WeatherApi
import com.skupis.weatherapp.data.CityInfo
import kotlinx.coroutines.launch
import timber.log.Timber

enum class WeatherApiStatus { LOADING, ERROR, DONE }

class CityViewModel : ViewModel() {
    private var lastTypedCity : String? = null

    private val _status = MutableLiveData<WeatherApiStatus>()

    val status: LiveData<WeatherApiStatus>
        get() = _status

    private val _cityInfo = MutableLiveData<List<CityInfo>>()

    val currentWeatherInfo: LiveData<List<CityInfo>>
        get() = _cityInfo

    fun showWeatherForCity(autoCompleteTextView : AutoCompleteTextView?) {
        val a = autoCompleteTextView?.run{
            val cityName = autoCompleteTextView.text.toString()
            lastTypedCity = cityName
            Timber.d("request for city ${autoCompleteTextView.text}")
            //Todo: save to db

            viewModelScope.launch {
                _status.value = WeatherApiStatus.LOADING
                try {
                    _status.value = WeatherApiStatus.DONE
                    _cityInfo.value = WeatherApi.retrofitService.getCityInfo(cityName=cityName)
                } catch (e: Exception) {
                    _status.value = WeatherApiStatus.ERROR
                    _cityInfo.value = ArrayList()
                }
            }

        }
    }
}