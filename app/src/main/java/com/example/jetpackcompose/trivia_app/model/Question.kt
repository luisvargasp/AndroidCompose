package com.example.jetpackcompose.trivia_app.model

data class Question(val question:String ,val answer:String,val category:String,
    val choices:List<String>,
)