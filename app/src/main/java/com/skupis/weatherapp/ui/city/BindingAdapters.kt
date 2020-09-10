package com.skupis.weatherapp.ui.city

import android.widget.Button
import android.widget.ImageButton
import androidx.databinding.BindingAdapter

@BindingAdapter("enableStatus")
fun bindImage(imageButton: Button, text : String) {
    imageButton.isEnabled = CityNameValidator.isValid(text)
}