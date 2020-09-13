package com.skupis.weatherapp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.skupis.weatherapp.databinding.WeatherFragmentBinding
import com.skupis.weatherapp.ui.CityViewModel
import java.util.*

class WeatherFragment : Fragment() {
    private val viewModel: CityViewModel by lazy {
        ViewModelProvider(requireActivity()).get(CityViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = WeatherFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.cityName.text = WeatherFragmentArgs.fromBundle(requireArguments())
            .cityName.let {
                it.substring(0, 1).toUpperCase(Locale.ENGLISH)
                    .plus(it.substring(1).toLowerCase(Locale.ENGLISH))
            }
        return binding.root
    }

}