package com.app.movies.core.data.reposimp

import com.app.movies.core.data.datasources.MoviesApiService
import com.app.movies.core.data.models.MovieCreditsResponse
import com.app.movies.core.data.models.MovieDetailsResponse
import com.app.movies.core.data.models.PopularMoviesResponse
import com.app.movies.core.data.models.SearchMoviesResponse
import com.app.movies.core.data.models.SimilarMoviesResponse
import com.app.movies.core.domain.repos.IMoviesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val apiService: MoviesApiService
) : IMoviesRepo {

    override fun getPopularMovies(language: String, page: Int): Flow<Result<PopularMoviesResponse>> = flow {
        try {
            val response = apiService.getPopularMovies(language, page)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Result.success(it))
                } ?: emit(Result.failure(Exception("No data available")))
            } else {
                emit(Result.failure(Exception("Error: ${response.message()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun searchMovies(query: String, page: Int): Flow<Result<SearchMoviesResponse>> = flow {
        try {
            val response = apiService.searchMovies(query, page)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Result.success(it))
                } ?: emit(Result.failure(Exception("No data available")))
            } else {
                emit(Result.failure(Exception("Error: ${response.message()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getMovieDetails(movieId: Int): Flow<Result<MovieDetailsResponse>> = flow {
        try {
            val response = apiService.getMovieDetails(movieId)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Result.success(it))
                } ?: emit(Result.failure(Exception("No data available")))
            } else {
                emit(Result.failure(Exception("Error: ${response.message()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getSimilarMovies(movieId: Int): Flow<Result<SimilarMoviesResponse>> = flow {
        try {
            val response = apiService.getSimilarMovies(movieId)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Result.success(it))
                } ?: emit(Result.failure(Exception("No data available")))
            } else {
                emit(Result.failure(Exception("Error: ${response.message()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
    override fun getMovieCredits(movieId: Int): Flow<Result<MovieCreditsResponse>> = flow {
        try {
            val response = apiService.getMovieCredits(movieId)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Result.success(it))
                } ?: emit(Result.failure(Exception("No data available")))
            } else {
                emit(Result.failure(Exception("Error: ${response.message()}")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
