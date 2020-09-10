package com.skupis.weatherapp.ui.city

import android.widget.AutoCompleteTextView

object CityNameValidator : AutoCompleteTextView.Validator {
    private val regex = Regex("^[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+(?:[\\s-][a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+)*\$")

    override fun isValid(input: CharSequence?): Boolean =
        if (input == null) false
        else regex.matches(input)

    override fun fixText(input: CharSequence?): CharSequence = input?:""
}