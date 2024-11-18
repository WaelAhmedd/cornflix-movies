package com.app.movies.core.network.di

import android.content.Context
import com.app.movies.core.base.ISharedPrefManager
import com.app.movies.core.base.SharedPrefManager
import com.app.movies.core.data.SessionServiceImpl
import com.app.movies.core.domain.SessionService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {


    @Binds
    @Singleton
    abstract fun bindSessionService(
        sessionService: SessionServiceImpl
    ): SessionService


    companion object {


        @Provides
        @Singleton
        fun provideSharedPrefManager(
            @ApplicationContext context: Context,
        ): ISharedPrefManager {
            return SharedPrefManager(context)
        }


    }
}