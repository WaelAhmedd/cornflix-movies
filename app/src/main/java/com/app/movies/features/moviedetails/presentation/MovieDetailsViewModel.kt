package com.app.movies.features.moviedetails.presentation

import androidx.lifecycle.viewModelScope
import com.app.movies.core.base.BaseViewModel
import com.app.movies.core.data.models.CastMember
import com.app.movies.core.data.models.CrewMember
import com.app.movies.core.domain.SessionService
import com.app.movies.features.moviedetails.domain.usecases.GetMovieCreditsUseCase
import com.app.movies.features.moviedetails.domain.usecases.GetMovieDetailsUseCase
import com.app.movies.features.moviedetails.domain.usecases.GetSimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    private val sessionService: SessionService
) : BaseViewModel<MovieDetailsViewState, MovieDetailsViewEvent, MovieDetailsViewEffect>() {

    private val _watchlist = MutableStateFlow<Set<Int>>(emptySet())
    val watchlist: StateFlow<Set<Int>> = _watchlist
    override fun setInitialState(): MovieDetailsViewState = MovieDetailsViewState.Init

    init {
        _watchlist.value = sessionService.getWatchlist()
    }
    override fun handleEvents(event: MovieDetailsViewEvent) {
        when (event) {
            is MovieDetailsViewEvent.LoadMovieDetails -> loadMovieDetails(event.movieId)

            is MovieDetailsViewEvent.ToggleWatchlist -> toggleWatchlist(event.movieId)
        }
    }



    private fun loadMovieDetails(movieId: Int) {
        setState { MovieDetailsViewState.Loading }
        viewModelScope.launch {
            val movieDetailsFlow = getMovieDetailsUseCase.invoke(movieId)
            val similarMoviesFlow = getSimilarMoviesUseCase.invoke(movieId)

            combine(movieDetailsFlow, similarMoviesFlow) { movieResult, similarMoviesResult ->
                Pair(movieResult, similarMoviesResult)
            }.collect { (movieResult, similarMoviesResult) ->
                val movie = movieResult.getOrNull()
                val similarMovies = similarMoviesResult.getOrNull()

                if (movie != null && similarMovies != null) {
                    val topSimilarMovies = similarMovies.results.take(5)

                    val castAndCrewFlows = topSimilarMovies.map { similarMovie ->
                        getMovieCreditsUseCase.invoke(similarMovie.id)
                    } + getMovieCreditsUseCase.invoke(movie.id)

                    combine(castAndCrewFlows) { creditsResults ->
                        creditsResults.mapNotNull { it.getOrNull() }
                    }.collect { allCredits ->
                        val allCast = allCredits.flatMap { it.cast }
                            .sortedByDescending { it.popularity }.take(5)

                        val allDirectors = allCredits.flatMap { it.crew }
                            .filter { it.job == "Director" }.sortedByDescending { it.popularity }.take(5)

                        setState {
                            MovieDetailsViewState.MovieDetailsLoaded(
                                movie = movie,
                                isInWatchlist = watchlist.value.contains(movie.id),
                                similarMovies = topSimilarMovies,
                                popularActors = allCast,
                                popularDirectors = allDirectors
                            )
                        }
                    }
                } else {
                    setState { MovieDetailsViewState.Failure("Failed to load movie details") }
                }
            }
        }
    }



    private fun toggleWatchlist(movieId: Int) {
        val currentWatchlist = watchlist.value.toMutableSet()
        if (currentWatchlist.contains(movieId)) {
            currentWatchlist.remove(movieId)
            sessionService.removeMovieFromWatchlist(movieId)
        } else {
            currentWatchlist.add(movieId)
            sessionService.addMovieToWatchlist(movieId)
        }
        _watchlist.value = currentWatchlist
    }
}
