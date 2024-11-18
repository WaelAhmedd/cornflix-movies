package com.app.movies.features.home.domain.usecases


import com.app.movies.core.data.models.SearchMoviesResponse
import com.app.movies.core.domain.repos.IMoviesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val moviesRepo: IMoviesRepo
) {
    operator fun invoke(query: String, page: Int = 1): Flow<Result<SearchMoviesResponse>> {
        return moviesRepo.searchMovies(query, page)
    }
}
