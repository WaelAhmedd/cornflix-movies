package com.app.movies

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.movies.features.home.presentation.HomeScreen
import com.app.movies.features.home.presentation.HomeViewState
import com.app.movies.features.moviedetails.presentation.MovieDetailsScreen
import com.app.movies.features.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigation(
    activity: Activity,
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navigateToHomeScreen = { navController.navigate(Screen.Home.route) })
        }
        composable(Screen.Home.route) {
            //   ReservationScreen()
            HomeScreen({ movieId ->
                navController.navigate(Screen.MovieDetails.withArgs(movieId))
            })
        }
        composable(
            Screen.MovieDetails.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.IntType
                nullable = false
            })
        ) { entry ->
            //   ReservationScreen()
            MovieDetailsScreen(movieId = entry.arguments?.getInt("movieId") ?: 0)
        }


    }
}