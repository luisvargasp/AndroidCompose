package com.example.jetpackcompose.weather_app.screens.about

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.jetpackcompose.R
import com.example.jetpackcompose.weather_app.widgets.WeatherAppBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutScreen(navController: NavController) {

    Scaffold(topBar = {WeatherAppBar(navController = navController, title = "About",icon= Icons.Default.ArrowBack, isMainScreen = false){
        navController.popBackStack()
    } }) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(text = stringResource(id = R.string.about_app), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            }

        }


    }
}