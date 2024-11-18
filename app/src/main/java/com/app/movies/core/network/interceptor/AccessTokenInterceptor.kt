package com.app.movies.core.network.interceptor


import com.app.movies.core.network.util.InterceptorUtils
import com.app.movies.core.domain.SessionService

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val sessionService: SessionService, private val apiKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        // Add the API key as a query parameter
        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        // Build a new request with the updated URL
        val requestWithApiKey = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(requestWithApiKey)

    }
}