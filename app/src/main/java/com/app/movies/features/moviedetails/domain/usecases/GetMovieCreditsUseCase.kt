package com.app.movies.features.moviedetails.domain.usecases

import com.app.movies.core.data.models.MovieCreditsResponse
import com.app.movies.core.domain.repos.IMoviesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val moviesRepo: IMoviesRepo
) {
    operator fun invoke(movieId: Int): Flow<Result<MovieCreditsResponse>> {
        return moviesRepo.getMovieCredits(movieId)
    }
}
