package com.example.jetpackcompose.weather_app.repository

import com.example.jetpackcompose.weather_app.data.WeatherDao
import com.example.jetpackcompose.weather_app.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)
    suspend fun getFavById(city: String): Favorite = weatherDao.getFavById(city)

    fun getUnits(): Flow<List<com.example.jetpackcompose.weather_app.model.Unit>> = weatherDao.getUnits()
    suspend fun insertUnit(unit: com.example.jetpackcompose.weather_app.model.Unit) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit: com.example.jetpackcompose.weather_app.model.Unit) = weatherDao.updateUnit(unit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()
    suspend fun deleteUnit(unit: com.example.jetpackcompose.weather_app.model.Unit) = weatherDao.deleteUnit(unit)



}