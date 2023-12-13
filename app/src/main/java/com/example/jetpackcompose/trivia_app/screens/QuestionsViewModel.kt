package com.example.jetpackcompose.trivia_app.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.trivia_app.data.DataOrException
import com.example.jetpackcompose.trivia_app.model.Question
import com.example.jetpackcompose.trivia_app.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(val repository: QuestionRepository):ViewModel() {
   val data: MutableState<DataOrException<List<Question>,Boolean,Exception>>  = mutableStateOf(
       DataOrException(null,true,Exception())
   )
    init {
        getAllQuestions()
    }

    private fun getAllQuestions(){
        viewModelScope.launch (Dispatchers.IO){
            withContext(Dispatchers.Main){
                data.value.loading=true
            }

            data.value= repository.getAllQuestions()

            if(data.value.data.toString().isNotEmpty()){
                //data.value.loading=false
            }

        }

    }
    fun getTotalQuestions()=data.value.data?.size

}