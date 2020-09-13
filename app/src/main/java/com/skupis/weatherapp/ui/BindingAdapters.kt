package com.skupis.weatherapp.ui

import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.skupis.weatherapp.R
import com.skupis.weatherapp.ui.city.CityNameValidator

@BindingAdapter("enableStatus")
fun enableStatus(imageButton: Button, text: String) {
    imageButton.isEnabled = CityNameValidator.isValid(text)
}

@BindingAdapter("temperatureText")
fun temperatureText(textView: TextView, tempValue: String?) {
    if (tempValue == null) return
    val context = textView.context
    textView.text = context.resources.getString(
        R.string.temperature_text,
        tempValue
    )
    with(tempValue.toFloat()) {
        textView.setTextColor(
            context.getColor(
                if (this < 10) {
                    R.color.colorBlue
                } else if (this > 20) {
                    R.color.colorRed
                } else {
                    R.color.colorGrey
                }
            )
        )
    }

}