package com.skupis.weatherapp.ui.city

import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.skupis.weatherapp.ui.CityViewModel

@BindingAdapter("enableStatus")
fun enable(imageButton: Button, text : String) {
    imageButton.isEnabled = CityNameValidator.isValid(text)
}

//@BindingAdapter("statusText")
//fun statusText(textView: TextView, text : String) {
//    textView.text = text
//}