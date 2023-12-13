package com.example.jetpackcompose.trivia_app.repository

import com.example.jetpackcompose.trivia_app.data.DataOrException
import com.example.jetpackcompose.trivia_app.model.Question
import com.example.jetpackcompose.trivia_app.remote.QuestionApiService
import javax.inject.Inject

class QuestionRepository @Inject constructor( val api: QuestionApiService) {

    private val listOfQuestions=ArrayList<Question>(emptyList())


   suspend fun getAllQuestions():DataOrException<List<Question>,Boolean,Exception>{
       return try {
           DataOrException(api.getAllQuestions(),false,null)
       }catch (exception:Exception){
           DataOrException(null,false,exception)

       }
    }


}