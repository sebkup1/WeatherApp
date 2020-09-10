package com.skupis.weatherapp.ui.city

import android.widget.AutoCompleteTextView
import androidx.lifecycle.ViewModel
import timber.log.Timber
import javax.inject.Singleton

//@Singleton
class CityViewModel : ViewModel() {
    private var lastTypedCity : String? = null
    fun showWeatherForCity(autoCompleteTextView : AutoCompleteTextView?) {
        val a = autoCompleteTextView?.run{
            lastTypedCity = autoCompleteTextView.text.toString()
            Timber.d("request for city ${autoCompleteTextView.text}")
        }
    }
}