package com.app.movies.core.domain.repos

import com.app.movies.core.data.models.MovieCreditsResponse
import com.app.movies.core.data.models.MovieDetailsResponse
import com.app.movies.core.data.models.PopularMoviesResponse
import com.app.movies.core.data.models.SearchMoviesResponse
import com.app.movies.core.data.models.SimilarMoviesResponse
import kotlinx.coroutines.flow.Flow

interface IMoviesRepo {
    fun getPopularMovies(
        language: String = "en-US",
        page: Int = 1
    ): Flow<Result<PopularMoviesResponse>>

    fun searchMovies(query: String, page: Int = 1): Flow<Result<SearchMoviesResponse>>
    fun getMovieDetails(movieId: Int): Flow<Result<MovieDetailsResponse>>
    fun getSimilarMovies(movieId: Int): Flow<Result<SimilarMoviesResponse>>
    fun getMovieCredits(movieId: Int): Flow<Result<MovieCreditsResponse>>
}
