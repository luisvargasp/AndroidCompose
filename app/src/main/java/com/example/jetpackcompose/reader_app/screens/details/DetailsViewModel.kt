package com.example.jetpackcompose.reader_app.screens.details

import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.reader_app.data.Resource
import com.example.jetpackcompose.reader_app.model.Item
import com.example.jetpackcompose.reader_app.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: BookRepository)

    : ViewModel(){
    suspend fun getBookInfo(bookId: String): Resource<Item> {
        return repository.getBookInfo(bookId)
    }
}