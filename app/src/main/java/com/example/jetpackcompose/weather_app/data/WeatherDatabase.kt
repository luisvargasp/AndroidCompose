package com.example.jetpackcompose.weather_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackcompose.weather_app.model.Favorite

@Database(entities = [Favorite::class, com.example.jetpackcompose.weather_app.model.Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}