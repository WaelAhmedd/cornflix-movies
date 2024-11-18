package com.app.movies.features.home.domain.usecases

import com.app.movies.core.data.models.PopularMoviesResponse
import com.app.movies.core.domain.repos.IMoviesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val moviesRepo: IMoviesRepo
) {
    operator fun invoke(language: String = "en-US", page: Int = 1): Flow<Result<PopularMoviesResponse>> {
        return moviesRepo.getPopularMovies(language, page)
    }
}
