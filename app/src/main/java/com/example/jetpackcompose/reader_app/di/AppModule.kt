package com.example.jetpackcompose.reader_app.di

import com.example.jetpackcompose.reader_app.network.BooksApi
import com.example.jetpackcompose.reader_app.repository.FireRepository
import com.example.jetpackcompose.reader_app.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideFireBookRepository()
            = FireRepository(queryBook = FirebaseFirestore.getInstance()
        .collection("books"))

    @Singleton
    @Provides
    fun provideBookApi(): BooksApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY)).build())

            .build()
            .create(BooksApi::class.java)
    }


}