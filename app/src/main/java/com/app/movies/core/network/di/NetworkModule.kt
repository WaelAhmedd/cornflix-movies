package com.app.movies.core.network.di


import android.content.Context
import com.app.movies.BuildConfig
import com.app.movies.core.network.interceptor.AccessTokenInterceptor
import com.app.movies.core.domain.SessionService
import com.app.movies.core.network.retrofit.Services

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideAccessTokenInterceptor(sessionService: SessionService): AccessTokenInterceptor {
        return AccessTokenInterceptor(sessionService, BuildConfig.API_KEY)
    }

    @Provides
    fun providesRetrofit(
        @ApplicationContext context: Context,
        accessTokenInterceptor: AccessTokenInterceptor,
        moshi: Moshi,
    ): Retrofit {
        return Services.getInstance(
            context,
            accessTokenInterceptor,
            moshi
        ).retrofit!!
    }


}