package com.example.jetpackcompose.trivia_app.screens

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.jetpackcompose.trivia_app.component.Questions

@Composable
fun TriviaHome(viewModel: QuestionsViewModel){
    Questions(viewModel)
}

