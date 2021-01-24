package com.example.covid19.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19.network.CountryData
import com.example.covid19.network.CovidApi
import com.example.covid19.network.Properties
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "MainViewModel"

class MainViewModel : ViewModel() {

    private val _total = MutableLiveData<Properties>()
    val total: LiveData<Properties>
        get() = _total

    private val _country = MutableLiveData<CountryData>()
    val country: LiveData<CountryData>
        get() = _country

    init {
        getTotalData()
    }

    private fun getTotalData() {
        viewModelScope.launch {
            try {
                val result = CovidApi.retrofitService.getTotalData()
                _total.value = result[0]

                val countryData = CovidApi.retrofitCountry.getCountryData()
                _country.value = countryData.response?.get(0)

                Log.d(TAG, "Fetch Data: Succeeded.")

            } catch (e: Exception) {
                Log.e(TAG, "Error fetching data : ${e.message}")
            }
        }
    }
}