package com.example.jetpackcompose.reader_app.navigation

import com.example.jetpackcompose.movie_app.navigation.MovieScreens

enum class ReaderScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    HomeScreen,
    SearchScreen,
    DetailsScreen,
    UpdateScreen,
    StatsScreen;

    companion object {
        fun fromRoute(route: String): ReaderScreens =
            when (route?.substringBefore("/")) {
                HomeScreen.name -> {
                    HomeScreen

                }

                DetailsScreen.name -> {
                    DetailsScreen
                }

                LoginScreen.name -> {
                    LoginScreen
                }

                CreateAccountScreen.name -> {
                    CreateAccountScreen
                }

                UpdateScreen.name -> {
                    UpdateScreen
                }

                StatsScreen.name -> {
                    StatsScreen
                }

                SplashScreen.name -> {
                    SplashScreen
                }

                SearchScreen.name -> {
                    SearchScreen
                }

                else -> {
                    throw IllegalArgumentException("invalid route")
                }

            }

    }

}

