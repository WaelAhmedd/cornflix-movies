package com.app.movies.core.domain.repos

import com.app.movies.core.data.models.PopularMoviesResponse
import kotlinx.coroutines.flow.Flow

interface IMoviesRepo {
    fun getPopularMovies(
        language: String = "en-US",
        page: Int = 1
    ): Flow<Result<PopularMoviesResponse>>


}
