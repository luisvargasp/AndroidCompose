package com.example.jetpackcompose.reader_app.network

import com.example.jetpackcompose.reader_app.model.Book
import com.example.jetpackcompose.reader_app.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApi {

    @GET("volumes")
    suspend fun getAllBooks(@Query("q") query: String): Book

    @GET("volumes/{bookId}")
    suspend fun getBookInfo(@Path("bookId") bookId: String): Item


}