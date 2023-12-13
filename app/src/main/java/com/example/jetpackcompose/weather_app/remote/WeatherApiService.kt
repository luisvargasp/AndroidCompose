package com.example.jetpackcompose.weather_app.remote

import com.example.jetpackcompose.weather_app.model.Weather
import com.example.jetpackcompose.weather_app.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("data/2.5/forecast")
    suspend fun getWeather(@Query("q") city:String,@Query("units") units:String="metric",

                           @Query("appid") appId:String=Constants.API_KEY,@Query("cnt") quantity :Int=5):Weather
}