package com.example.jetpackcompose.trivia_app.di

import com.example.jetpackcompose.trivia_app.remote.QuestionApiService
import com.example.jetpackcompose.trivia_app.repository.QuestionRepository
import com.example.jetpackcompose.trivia_app.util.Constants
import com.google.gson.Gson
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
    @Provides
    @Singleton
    fun provideQuestionApiService():QuestionApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .build()
            .create(QuestionApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideQuestionRepository(apiService: QuestionApiService)=QuestionRepository(apiService)
}