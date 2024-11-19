package com.app.movies.features.home.presentation

import androidx.lifecycle.viewModelScope
import com.app.movies.core.base.BaseViewModel
import com.app.movies.core.data.models.Movie
import com.app.movies.core.data.models.PopularMoviesResponse
import com.app.movies.core.data.models.SearchMoviesResponse
import com.app.movies.core.domain.SessionService
import com.app.movies.features.home.domain.usecases.GetPopularMoviesUseCase
import com.app.movies.features.home.domain.usecases.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val sessionService: SessionService
) : BaseViewModel<HomeViewState, HomeViewEvent, HomeViewEffect>() {

    private var currentPage = 1
    private var isLastPage = false
    private var isLoadingMore = false

    private val _watchlist = MutableStateFlow<Set<Int>>(emptySet())
    val watchlist: StateFlow<Set<Int>> = _watchlist

    init {

    }

    override fun setInitialState(): HomeViewState = HomeViewState.Init

    override fun handleEvents(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.SearchMovies -> searchMovies(event.query)
            is HomeViewEvent.LoadNextPage -> loadNextPage()
            is HomeViewEvent.ToggleWatchlist -> toggleWatchlist(event.movieId)
            HomeViewEvent.LoadPopularMovies -> loadPopularMovies()
        }
    }

    private fun loadPopularMovies() {
        resetPagination()
        loadPopularMoviesData(append = false)
    }

    private fun searchMovies(query: String) {
        if (query.isBlank()) {
            loadPopularMovies()
            return
        }
        resetPagination()
        loadSearchMoviesData(query = query, append = false)
    }

    private fun loadNextPage() {
        if (isLoadingMore || isLastPage) return

        isLoadingMore = true
        currentPage++

        val currentState = getState()
        if (currentState is HomeViewState.HomeScreenLoaded) {
            if (currentState.searchQuery.isNullOrEmpty()) {
                loadPopularMoviesData(append = true)
            } else {
                loadSearchMoviesData(query = currentState.searchQuery, append = true)
            }
        }
    }

    private fun loadPopularMoviesData(append: Boolean) {
        fetchData(
            fetchMovies = { getPopularMoviesUseCase(page = currentPage) },
            append = append,
            searchQuery = null,
            transformResponse = { response ->
                (response as PopularMoviesResponse).results
            }
        )
    }

    private fun loadSearchMoviesData(query: String, append: Boolean) {
        fetchData(
            fetchMovies = { searchMoviesUseCase(query = query, page = currentPage) },
            append = append,
            searchQuery = query,
            transformResponse = { response ->
                val movies = (response as SearchMoviesResponse).results
                if (movies.isEmpty() && !append) {
                    // Fallback to popular movies if no results and not paginating
                    loadPopularMovies()
                    return@fetchData emptyList()
                }
                movies
            }
        )
    }

    private fun <T> fetchData(
        fetchMovies: suspend () -> Flow<Result<T>>,
        append: Boolean,
        searchQuery: String?,
        transformResponse: (T) -> List<Movie>
    ) {
        if (!append) setState { HomeViewState.Loading }

        viewModelScope.launch {
            fetchMovies().collect { result ->
                result.fold(
                    onSuccess = { response ->
                        val movies = transformResponse(response)
                        if (movies.isEmpty()) {
                            if (!append) setState { HomeViewState.Empty }
                            isLastPage = true
                        } else {
                            val groupedMovies = movies.groupBy { movie ->
                                movie.releaseDate.take(4).toIntOrNull() ?: 0
                            }

                            val currentGroupedMovies = if (append) {
                                val currentState = getState()
                                if (currentState is HomeViewState.HomeScreenLoaded) {
                                    currentState.groupedMovies.toMutableMap().apply {
                                        groupedMovies.forEach { (key, movies) ->
                                            merge(key, movies) { old, new -> old + new }
                                        }
                                    }
                                } else groupedMovies
                            } else groupedMovies

                            setState {
                                HomeViewState.HomeScreenLoaded(
                                    groupedMovies = currentGroupedMovies,
                                    watchlistIds = watchlist.value,
                                    searchQuery = searchQuery
                                )
                            }
                        }
                    },
                    onFailure = { error ->
                        setState {
                            HomeViewState.Failure(
                                error.message ?: "Failed to load movies."
                            )
                        }
                        setEffect { HomeViewEffect.ShowErrorToast }
                    }
                )
                isLoadingMore = false
            }
        }
    }

    private fun resetPagination() {
        currentPage = 1
        isLastPage = false
        isLoadingMore = false
    }

    private fun toggleWatchlist(movieId: Int) {
        val currentWatchlist = _watchlist.value.toMutableSet()
        if (currentWatchlist.contains(movieId)) {
            currentWatchlist.remove(movieId)
            sessionService.removeMovieFromWatchlist(movieId)
        } else {
            currentWatchlist.add(movieId)
            sessionService.addMovieToWatchlist(movieId)
        }
        _watchlist.value = currentWatchlist
    }

    fun refreshWatchlist() {
        _watchlist.value = sessionService.getWatchlist()    }
}
