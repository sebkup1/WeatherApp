package com.skupis.weatherapp.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.skupis.weatherapp.R
import com.skupis.weatherapp.databinding.CityFragmentBinding
import com.skupis.weatherapp.ui.CityViewModel
import com.skupis.weatherapp.ui.WeatherApiStatus


class CityFragment : Fragment() {
    lateinit var viewModel: CityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CityFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity()).get(CityViewModel::class.java)
        binding.viewModel = viewModel
        val adapter = ArrayAdapter(
            requireActivity().applicationContext,
            android.R.layout.simple_expandable_list_item_1,
            requireContext().resources.getStringArray(R.array.cities)
        )
        binding.autoCompleteTextView.apply {
            setAdapter(adapter)
            threshold = 1
        }
        viewModel.status.observe(viewLifecycleOwner, Observer {
            binding.textView.text = when (it) {
                WeatherApiStatus.SEARCHING -> requireContext().resources.getString(R.string.searching_city_text)
                WeatherApiStatus.ERROR -> requireContext().resources.getString(R.string.service_unavailable_text)
                WeatherApiStatus.NOT_FOUND -> requireContext().resources.getString(R.string.city_not_found_text)
                else -> binding.textView.text
            }
            if (it == WeatherApiStatus.CITY_FOUND) {
                findNavController().navigate(
                    CityFragmentDirections.actionCityFragmentToWeatherFragment(
                        binding.autoCompleteTextView.text.toString()
                    )
                )
                viewModel.requestHandled()
            }
        })
        viewModel.currentCityWeatherInfo.observe(viewLifecycleOwner, Observer {
            binding.textView.text = it[0].WeatherText
        })
        return binding.root
    }

}