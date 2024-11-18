package com.app.movies.core.di

import com.app.movies.core.data.datasources.MoviesApiService
import com.app.movies.core.data.reposimp.MoviesRepoImpl
import com.app.movies.core.domain.repos.IMoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMoviesRepo(apiService: MoviesApiService): IMoviesRepo {
        return MoviesRepoImpl(apiService)
    }
}
