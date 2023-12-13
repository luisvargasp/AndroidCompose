package com.example.jetpackcompose.reader_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.reader_app.screens.details.ReaderBookDetailsScreen
import com.example.jetpackcompose.reader_app.screens.home.HomeScreenViewModel
import com.example.jetpackcompose.reader_app.screens.home.ReaderHomeScreen
import com.example.jetpackcompose.reader_app.screens.login.ReaderLoginScreen
import com.example.jetpackcompose.reader_app.screens.search.ReaderBookSearchScreen
import com.example.jetpackcompose.reader_app.screens.splash.ReaderSplashScreen
import com.example.jetpackcompose.reader_app.screens.stats.ReaderStatsScreen
import com.example.jetpackcompose.reader_app.screens.update.ReaderUpdateScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun  ReaderNavigation() {
    val navController=rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name ){

        composable(ReaderScreens.SplashScreen.name){
            ReaderSplashScreen(navController)
        }

        composable(ReaderScreens.HomeScreen.name){
            ReaderHomeScreen(navController)

        }
        composable(ReaderScreens.LoginScreen.name){
            ReaderLoginScreen(navController)

        }
        composable(ReaderScreens.StatsScreen.name){
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            ReaderStatsScreen(navController = navController, viewModel = homeViewModel)

        }
        val updateName = ReaderScreens.UpdateScreen.name

        composable("$updateName/{bookItemId}",
            arguments = listOf(navArgument("bookItemId") {
                type = NavType.StringType
            })) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("bookItemId").let {
                ReaderUpdateScreen(navController = navController, bookItemId = it.toString())
            }

        }
        composable(ReaderScreens.CreateAccountScreen.name){

        }
        val detailName = ReaderScreens.DetailsScreen.name
        composable("$detailName/{bookId}", arguments = listOf(navArgument("bookId"){
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let {

                ReaderBookDetailsScreen(navController = navController, bookId = it.toString())
            }
        }
        composable(ReaderScreens.SearchScreen.name){
            ReaderBookSearchScreen(navController = navController)

        }

    }
}