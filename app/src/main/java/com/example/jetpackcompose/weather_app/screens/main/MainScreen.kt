package com.example.jetpackcompose.weather_app.screens.main

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavController
import com.example.jetpackcompose.trivia_app.data.DataOrException
import com.example.jetpackcompose.weather_app.model.Weather
import com.example.jetpackcompose.weather_app.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel,city: String?){

    val weather= produceState<DataOrException<Weather,Boolean,Exception>>(initialValue =DataOrException(loading = true) , producer = {
        value= mainViewModel.getWeather(city?: "Lima")
    }).value
    if(weather.loading==true){
        CircularProgressIndicator()
    }else if (weather.data!=null){
        MainScaffold(weather=weather.data!!,navController=navController)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather: Weather,navController: NavController) {
  Scaffold (topBar = {

      WeatherAppBar(navController=navController)


  }

  ){
      MainContent(data=weather)
      it

  }

}

@Composable
fun MainContent(data: Weather) {


}
