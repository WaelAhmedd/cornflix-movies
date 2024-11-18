package com.app.movies.features.home.presentation

import android.view.View
import com.app.movies.core.base.ViewEvent
import com.app.movies.core.base.ViewSideEffect
import com.app.movies.core.base.ViewState
import com.app.movies.core.data.models.Movie


sealed class HomeViewState :ViewState{
    object Init : HomeViewState()
    object Loading : HomeViewState()
    object Empty : HomeViewState()
    data class Failure(val errorMessage: String) : HomeViewState()
    data class HomeScreenLoaded(
        val groupedMovies: Map<Int, List<Movie>>,
        val watchlistIds: Set<Int>,
        val searchQuery: String? = null // Indicates if it's a search result or popular movies
    ) : HomeViewState()
}

sealed class HomeViewEvent:ViewEvent {
    object LoadPopularMovies : HomeViewEvent()
    data class SearchMovies(val query: String) : HomeViewEvent()
    data class ToggleWatchlist(val movieId: Int) : HomeViewEvent()
    object LoadNextPage : HomeViewEvent() // New event for pagination
}


sealed class HomeViewEffect : ViewSideEffect {
    object ShowErrorToast : HomeViewEffect()
}
