package com.app.movies.features.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.movies.R
import com.app.movies.features.home.presentation.components.MovieItem
import com.app.movies.features.home.presentation.components.SearchBar

@Composable
fun HomeScreen(
    navigateToMovieDetailsScreen: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.viewState.collectAsState()
    var isSearchBarVisible by remember { mutableStateOf(false) }
    val watchlist by viewModel.watchlist.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.refreshWatchlist()
        viewModel.setEvent(HomeViewEvent.LoadPopularMovies)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Corn",
                modifier = Modifier.height(200.dp).width(200.dp)
            )

            IconButton(
                onClick = { isSearchBarVisible = !isSearchBarVisible }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Gray
                )
            }
        }

        if (isSearchBarVisible) {
            SearchBar(
                onSearch = { query ->
                    viewModel.setEvent(HomeViewEvent.SearchMovies(query))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }

        when (state) {
            is HomeViewState.Init,HomeViewState.Loading  -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }

            is HomeViewState.Empty -> {
                Text(
                    text = "No results found",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }

            is HomeViewState.Failure -> {
                val errorMessage = (state as HomeViewState.Failure).errorMessage
                Text(
                    text = "Error: $errorMessage",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }

            is HomeViewState.HomeScreenLoaded -> {
                val groupedMovies = (state as HomeViewState.HomeScreenLoaded).groupedMovies

                LazyColumn {
                    groupedMovies.forEach { (year, movies) ->
                        item {
                            Text(
                                text = year.toString(),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        itemsIndexed(movies) { index, movie ->
                            MovieItem(
                                movie = movie,
                                isInWatchlist = watchlist.contains(movie.id),
                                onToggleWatchlist = {
                                    viewModel.setEvent(HomeViewEvent.ToggleWatchlist(movie.id))
                                },
                                navigateToMovies = { id -> navigateToMovieDetailsScreen.invoke(id) }
                            )

                            if (index == movies.lastIndex) {
                                LaunchedEffect(Unit) {
                                    viewModel.setEvent(HomeViewEvent.LoadNextPage)
                                }
                            }
                        }
                    }

                }

            }
        }
    }
}
