package com.skupis.weatherapp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.skupis.weatherapp.databinding.WeatherFragmentBinding
import com.skupis.weatherapp.ui.CityViewModel
import timber.log.Timber
import javax.inject.Singleton


class WeatherFragment : Fragment() {
    //    private val viewModel: CityViewModel by lazy {
//        ViewModelProvider(this).get(CityViewModel::class.java)
//    }
    lateinit var viewModel: CityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = WeatherFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity()).get(CityViewModel::class.java)
        viewModel.status.observe(viewLifecycleOwner, Observer {
            Timber.d("dsfasdfsda")
        })
        viewModel.currentCityWeatherInfo.observe(viewLifecycleOwner, Observer {
            binding.textView2.text = it[0].WeatherText
        })
        return binding.root
    }

}