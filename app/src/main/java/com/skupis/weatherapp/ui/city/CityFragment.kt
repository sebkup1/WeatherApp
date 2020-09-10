package com.skupis.weatherapp.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.skupis.weatherapp.R
import com.skupis.weatherapp.databinding.CityFragmentBinding


class CityFragment : Fragment() {

    private lateinit var viewModel: CityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CityFragmentBinding.inflate(inflater)
        binding.viewModel = ViewModelProvider(this).get(CityViewModel::class.java)
        val adapter = ArrayAdapter(
            requireActivity().applicationContext,
            android.R.layout.simple_expandable_list_item_1,
            requireContext().resources.getStringArray(R.array.cities)
        )
        binding.autoCompleteTextView.apply {
            setAdapter(adapter)
            threshold = 1
        }
        return binding.root
    }

}