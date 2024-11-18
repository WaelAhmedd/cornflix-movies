package com.app.movies.core.data.datasources

import com.app.movies.core.data.models.PopularMoviesResponse
import com.app.movies.core.data.models.SearchMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<PopularMoviesResponse>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): Response<SearchMoviesResponse>

}
