package com.example.jetpackcompose.weather_app.di

import android.content.Context
import androidx.room.Room
import com.example.jetpackcompose.weather_app.data.WeatherDao
import com.example.jetpackcompose.weather_app.data.WeatherDatabase
import com.example.jetpackcompose.weather_app.remote.WeatherApiService
import com.example.jetpackcompose.weather_app.repository.WeatherRepository
import com.example.jetpackcompose.weather_app.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi()=Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY)).build())

        .build()
        .create(WeatherApiService::class.java)

    @Singleton
    @Provides
    fun provideWeatherRepository(api:WeatherApiService)= WeatherRepository(api)

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao
            = weatherDatabase.weatherDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase
            = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        "weather_database")
        .fallbackToDestructiveMigration()
        .build()




}