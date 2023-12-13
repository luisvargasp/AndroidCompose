package com.example.jetpackcompose.weather_app.repository

import android.util.Log
import com.example.jetpackcompose.trivia_app.data.DataOrException
import com.example.jetpackcompose.weather_app.model.Weather
import com.example.jetpackcompose.weather_app.remote.WeatherApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(val api:WeatherApiService){

    suspend fun getWeather(city:String):DataOrException<Weather,Boolean,Exception>{

        val response=try{
            api.getWeather(city)

        }catch (e:Exception){
            Log.d("ErrorEx", "${e.message} ")
            return DataOrException(e=e)

        }
        return DataOrException(data = response)

    }
}