package com.example.jetpackcompose.movie_app.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.request.SuccessResult
import coil.transform.CircleCropTransformation
import com.example.jetpackcompose.movie_app.model.Movie
import com.example.jetpackcompose.movie_app.model.getMovies
import com.example.jetpackcompose.movie_app.navigation.MovieScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Scaffold (topBar ={
        TopAppBar(title = { Text(text = "Movies App Nav") },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray)
        )

    }, bottomBar ={
        BottomAppBar(
            content={
            }
        )
    }
    ){paddings->
        Box(modifier = Modifier.padding(paddings)) {
            MainContent(navController)
        }
    }

}

@Composable
fun MainContent(navController: NavController,movieList:List<Movie> = getMovies() ){
    val context = LocalContext.current
    Column(modifier = Modifier
        .padding(12.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxHeight()){
            items(movieList){
                MovieRow(movie = it){movie->
                    navController.navigate(MovieScreens.DetailsScreen.name+"/$movie"){
                        popUpTo(MovieScreens.HomeScreen.name){
                            inclusive=false

                        }
                    }
                }
            }
        }

    }

}
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRow(movie:Movie= getMovies()[0], onItemClick:(String)->Unit={}){
    val expanded= rememberSaveable{
        mutableStateOf(false)
    }
    Card (modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        //.height(130.dp)
        , shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp), onClick = {
            onItemClick(movie.id)

        }

    ){
        Row(modifier =Modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center ) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp)
                .clip(CircleShape) ,
                shape = RectangleShape, shadowElevation = 4.dp

            ) {
                Image(rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
                        .crossfade(true)
                        .listener(object : ImageRequest.Listener {
                            override fun onCancel(request: ImageRequest) {

                            }

                            override fun onError(request: ImageRequest, result: ErrorResult) {
                                super.onError(request, result)
                            }

                            override fun onStart(request: ImageRequest) {

                            }

                            override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                                super.onSuccess(request, result)
                            }
                        })
                        .transformations(CircleCropTransformation())
                        .build()


                ), contentDescription = "Img" )

            }
            Column (modifier = Modifier.padding(4.dp)){
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(text = "Director ${movie.director}", style = MaterialTheme.typography.titleSmall)
                Text(text = "Release: ${movie.year}", style = MaterialTheme.typography.titleSmall)
                AnimatedVisibility(visible = expanded.value) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style= SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                                append("Plot:")
                            }
                            withStyle(style= SpanStyle(color = Color.DarkGray, fontSize = 13.sp,fontWeight = FontWeight.Light)){
                                append(movie.plot)
                            }


                        }, modifier = Modifier.padding(vertical =6.dp))
                        Divider(modifier = Modifier.padding(vertical = 2.dp))
                        Text(text = "Director : ${movie.director}", style = MaterialTheme.typography.labelMedium)
                        Text(text = "Actors : ${movie.actors}", style = MaterialTheme.typography.labelMedium)
                        Text(text = "Rating : ${movie.rating}", style = MaterialTheme.typography.labelMedium)
                    }

                }




                Icon(imageVector =
                if(expanded.value) Icons.Filled.KeyboardArrowUp  else

                Icons.Filled.KeyboardArrowDown

                    ,contentDescription = "arrow",
                    modifier= Modifier
                        .size(24.dp)
                        .clickable {
                            expanded.value = expanded.value.not()
                        }, tint = Color.DarkGray
                )
            }

        }


    }
}
