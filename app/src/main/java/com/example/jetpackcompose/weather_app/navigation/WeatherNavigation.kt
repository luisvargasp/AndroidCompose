package com.example.jetpackcompose.weather_app.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.weather_app.screens.about.AboutScreen
import com.example.jetpackcompose.weather_app.screens.favorite.FavouriteScreen
import com.example.jetpackcompose.weather_app.screens.main.MainScreen
import com.example.jetpackcompose.weather_app.screens.main.MainViewModel
import com.example.jetpackcompose.weather_app.screens.search.SearchScreen
import com.example.jetpackcompose.weather_app.screens.settings.SettingsScreen
import com.example.jetpackcompose.weather_app.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController= rememberNavController()
    val navHost= NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){

        composable(WeatherScreens.SplashScreen.name){

            WeatherSplashScreen(navController=navController)

        }
        val route=WeatherScreens.MainScreen.name
        composable("$route/{city}", arguments = listOf(
            navArgument("city" ){
                type= NavType.StringType
            }
        )){
            it.arguments?.getString("city").let {city->

                val mainViewModel=hiltViewModel<MainViewModel>()
                MainScreen(navController = navController,mainViewModel,city)
            }



        }
        composable(WeatherScreens.AboutScreen.name){
            AboutScreen(navController = navController)

        }
        composable(WeatherScreens.FavoriteScreen.name){
            FavouriteScreen(navController)

        }
        composable(WeatherScreens.SettingsScreen.name){
            SettingsScreen(navController = navController)

        }
        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController = navController)

        }


    }


}