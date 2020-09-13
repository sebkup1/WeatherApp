package com.skupis.weatherapp.ui.city

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.skupis.weatherapp.R
import com.skupis.weatherapp.databinding.CityFragmentBinding
import com.skupis.weatherapp.ui.CityViewModel
import com.skupis.weatherapp.ui.WeatherApiStatus


class CityFragment : Fragment() {

    private val viewModel: CityViewModel by lazy {
        ViewModelProvider(requireActivity()).get(CityViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CityFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = ArrayAdapter(
            requireActivity().applicationContext,
            android.R.layout.simple_expandable_list_item_1,
            requireContext().resources.getStringArray(R.array.cities)
        )
        binding.autoCompleteTextView.apply {
            setAdapter(adapter)
            threshold = 1
        }
        viewModel.status.observe(viewLifecycleOwner, {
            binding.statusLabel.text = when (it) {
                WeatherApiStatus.SEARCHING -> requireContext().resources.getString(R.string.searching_weather_text)
                WeatherApiStatus.ERROR -> requireContext().resources.getString(R.string.service_unavailable_text)
                WeatherApiStatus.NOT_FOUND -> requireContext().resources.getString(R.string.city_not_found_text)
                else -> binding.statusLabel.text
            }
            if (it == WeatherApiStatus.CITY_FOUND) {
                findNavController().navigate(
                    CityFragmentDirections.actionCityFragmentToWeatherFragment(
                        binding.autoCompleteTextView.text.toString()
                    )
                )
                binding.statusLabel.text = ""
                viewModel.requestHandled()
            }
        })

        with(binding.autoCompleteTextView) {
            setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    val imm: InputMethodManager =
                        requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    if (binding.showWeatherButton.isEnabled) {
                        imm.hideSoftInputFromWindow(this.windowToken, 0)
                        clearFocus()
                        binding.showWeatherButton.performClick()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            resources.getText(R.string.city_name_not_allowed_text),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                return@setOnKeyListener false
            }
        }
        return binding.root
    }

}