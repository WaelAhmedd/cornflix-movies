package com.app.movies.features.moviedetails.presentation

import com.app.movies.core.base.ViewEvent
import com.app.movies.core.base.ViewSideEffect
import com.app.movies.core.base.ViewState
import com.app.movies.core.data.models.CastMember
import com.app.movies.core.data.models.CrewMember
import com.app.movies.core.data.models.Movie
import com.app.movies.core.data.models.MovieDetailsResponse

sealed class MovieDetailsViewState : ViewState {
    object Init : MovieDetailsViewState()
    object Loading : MovieDetailsViewState()
    data class MovieDetailsLoaded(
        val movie: MovieDetailsResponse,
        val isInWatchlist: Boolean,
        val similarMovies: List<Movie>,
        val popularActors: List<CastMember>,
        val popularDirectors: List<CrewMember>
    ) : MovieDetailsViewState()


    data class Failure(val errorMessage: String) : MovieDetailsViewState()
}

sealed class MovieDetailsViewEvent : ViewEvent {
    data class LoadMovieDetails(val movieId: Int) : MovieDetailsViewEvent()
    data class ToggleWatchlist(val movieId: Int) : MovieDetailsViewEvent()
}

sealed class MovieDetailsViewEffect : ViewSideEffect {
    object ShowErrorToast : MovieDetailsViewEffect()
}

