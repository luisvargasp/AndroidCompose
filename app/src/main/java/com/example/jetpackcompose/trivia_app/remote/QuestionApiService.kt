package com.example.jetpackcompose.trivia_app.remote

import com.example.jetpackcompose.trivia_app.model.Question
import retrofit2.http.GET

interface QuestionApiService {
    @GET("world.json")
    suspend fun getAllQuestions():List<Question>

}