package com.example.jetpackcompose.reader_app.screens.search

import android.annotation.SuppressLint
import android.graphics.fonts.FontStyle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.CircleCropTransformation
import com.example.jetpackcompose.reader_app.components.InputField
import com.example.jetpackcompose.reader_app.components.ReaderAppBar
import com.example.jetpackcompose.reader_app.model.Item
import com.example.jetpackcompose.reader_app.navigation.ReaderScreens


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@Composable
fun ReaderBookSearchScreen(navController: NavController,
                           viewModel: BooksSearchViewModel = hiltViewModel()
) {

    Scaffold(topBar = {
        ReaderAppBar(title = "Search Books",
            icon = Icons.Default.ArrowBack,
            navController = navController,
            showProfile = false){
            navController.popBackStack()
            //navController.navigate(ReaderScreens.HomeScreen.name)
        }
    }) {
        Surface(modifier = Modifier.padding(it)) {
            Column {
                SearchForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)){ searchQuery ->
                    viewModel.searchBooks(query = searchQuery)

                }
                Spacer(modifier = Modifier.height(13.dp))
                BookList(navController = navController)

            }


        }
    }

}

@Composable
fun BookList(navController: NavController,
             viewModel: BooksSearchViewModel = hiltViewModel()) {


    val listOfBooks = viewModel.list
    if (viewModel.isLoading){
        Row(
            modifier = Modifier.padding(end = 2.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically) {
            LinearProgressIndicator()
            Text(text = "Loading...")
        }

    }else {
        LazyColumn(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ){
            items(items = listOfBooks) { book ->
                BookRow(book, navController)

            }

        }
    }

}

@Composable
fun BookRow(
    book: Item,
    navController: NavController) {
    Card(modifier = Modifier
        .clickable {
            navController.navigate(ReaderScreens.DetailsScreen.name + "/${book.id}")
        }
        .fillMaxWidth()
        //.height(100.dp)
        .padding(3.dp),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)) {
        Row(modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.Top) {

            var imageUrl=""



            try {
                 imageUrl=book.volumeInfo.imageLinks.smallThumbnail

            }catch (e:Exception){
                imageUrl=  "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=80&q=80"
            }
            Image(
                painter = rememberAsyncImagePainter(  model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
                    .crossfade(true)
                    .build()),
                contentDescription = "book image",
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
                    .padding(end = 4.dp),
            )

            Column {
                Text(text = book.volumeInfo.title, overflow = TextOverflow.Ellipsis)
                Text(text =  "Author: ${book.volumeInfo.authors}",
                    overflow = TextOverflow.Clip,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    style = MaterialTheme.typography.labelMedium)

                Text(text =  "Date: ${book.volumeInfo.publishedDate}",
                    overflow = TextOverflow.Clip,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    style = MaterialTheme.typography.labelMedium)

                Text(text =  "${book.volumeInfo.categories}",
                    overflow = TextOverflow.Clip,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    style = MaterialTheme.typography.labelMedium)






            }

        }

    }

}


@ExperimentalComposeUiApi
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String) -> Unit = {}) {
    Column {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()

        }

        InputField(valueState = searchQueryState,
            labelId = "Search",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            })

    }


}