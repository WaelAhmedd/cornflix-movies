package com.app.movies.features.moviedetails.domain.usecases

import com.app.movies.core.domain.repos.IMoviesRepo
import com.app.movies.features.home.domain.usecases.GetPopularMoviesUseCase
import com.app.movies.features.home.domain.usecases.SearchMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object MovieDetailsUseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetMovieDetailsUseCase(moviesRepo: IMoviesRepo): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(moviesRepo)
    }

    @Provides
    @ViewModelScoped
    fun provideGetSimilarMoviesUseCase(moviesRepo: IMoviesRepo): GetSimilarMoviesUseCase {
        return GetSimilarMoviesUseCase(moviesRepo)
    }

    @Provides
    @ViewModelScoped
    fun provideGetMovieCreditsUseCase(moviesRepo: IMoviesRepo): GetMovieCreditsUseCase {
        return GetMovieCreditsUseCase(moviesRepo)
    }
}
