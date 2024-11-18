package com.app.movies.features.home.domain.usecases.di

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
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideSearchMoviesUseCase(moviesRepo: IMoviesRepo): SearchMoviesUseCase {
        return SearchMoviesUseCase(moviesRepo)
    }

    @Provides
    @ViewModelScoped
    fun provideGetPopularMoviesUseCase(moviesRepo: IMoviesRepo): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(moviesRepo)
    }
}
