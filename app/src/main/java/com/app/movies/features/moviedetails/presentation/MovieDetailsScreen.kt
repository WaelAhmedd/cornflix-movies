package com.app.movies.features.moviedetails.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.viewState.collectAsState()
    val watchlist by viewModel.watchlist.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.setEvent(MovieDetailsViewEvent.LoadMovieDetails(movieId))
    }

    Column(
        modifier = Modifier
            .fillMaxSize().background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        when (state) {
            is MovieDetailsViewState.Init -> Text("Initializing...")
            is MovieDetailsViewState.Loading ->
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.Red,
                        modifier = Modifier.size(50.dp)
                    )
                }


            is MovieDetailsViewState.MovieDetailsLoaded -> {
                val movie = (state as MovieDetailsViewState.MovieDetailsLoaded).movie
                MovieDetailsHeader(
                    movie,
                    isInWatchlist = watchlist.contains(movie.id)
                ) {
                    viewModel.setEvent(MovieDetailsViewEvent.ToggleWatchlist(movie.id))
                }
                SimilarMoviesSection((state as MovieDetailsViewState.MovieDetailsLoaded).similarMovies)
                val casts = (state as MovieDetailsViewState.MovieDetailsLoaded)
                CastSection(casts.popularActors, casts.popularDirectors)
            }


            is MovieDetailsViewState.Failure -> {
                val errorMessage = (state as MovieDetailsViewState.Failure).errorMessage
                Text(
                    "Error: $errorMessage",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

