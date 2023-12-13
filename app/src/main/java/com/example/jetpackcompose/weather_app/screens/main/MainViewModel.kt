package com.example.jetpackcompose.weather_app.screens.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.trivia_app.data.DataOrException
import com.example.jetpackcompose.weather_app.model.Weather
import com.example.jetpackcompose.weather_app.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: WeatherRepository )   :ViewModel() {




    suspend  fun getWeather(city: String):DataOrException<Weather,Boolean,Exception> {
      return repository.getWeather(city)

    }
}