package com.example.jetpackcompose.weather_app.screens.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcompose.weather_app.model.Favorite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(navController: NavController) {
    Scaffold(){
        it
        Surface (modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()){
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                val list = hiltViewModel<FavoriteViewModel>().favList.collectAsState().value
                LazyColumn(){
                    items(items=list){
                        CityRow(it,navController)
                    }
                }

            }

        }

    }
}

@Composable
fun CityRow(it: Favorite, navController: NavController) {
    Surface (modifier = Modifier.padding(3.dp).fillMaxWidth().height(50.dp).clickable {  }, shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color(0xFFB2DFDB)
    ){


    }

}
