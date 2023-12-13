package com.example.jetpackcompose.movie_app.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcompose.movie_app.model.Movie
import com.example.jetpackcompose.movie_app.model.getMovies
import com.example.jetpackcompose.movie_app.screens.home.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, id: String?){

    Scaffold(topBar ={
        TopAppBar(title = {

            Row (verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", modifier = Modifier.clickable {
                    navController.popBackStack()
                })
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Movies App Nav")
            }
                          },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray)
        )

    }){
        Surface (modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(it)){

            val movie= getMovies().find {movie -> movie.id==id }

            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                MovieRow(movie=movie!!)
                Text(text =movie?.title ?:"", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Movie Images")
                HorizontalScrollableImage(movie)


            }

        }
    }


}
@Preview
@Composable
private fun HorizontalScrollableImage(movie: Movie=getMovies()[0]) {
    LazyRow() {
        items(movie.images) { imageUrl ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .wrapContentSize()
                    ,
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)

            ) {
                Image(painter = rememberAsyncImagePainter(imageUrl), contentDescription = "img", modifier = Modifier.size(200.dp) )
            }

        }
    }
}