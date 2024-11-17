package com.app.movies.core.network.interceptor


import com.app.movies.core.network.util.InterceptorUtils
import com.app.movies.core.domain.SessionService

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(
    private val sessionService: SessionService
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        if (InterceptorUtils.hasHeader(
                chain,
                AppHeaders.HEADER_ACCESS_TOKEN_PLACEHOLDER
            )
        ) {
//            sessionService.getAccessToken()
//                ?.let {
//                    InterceptorUtils.setHeader(
//                        builder,
//                        AppHeaders.HEADER_ACCESS_TOKEN_PLACEHOLDER,
//                        "Bearer $it"
//                    )
//                }
        }

        return chain.proceed(builder.build())

    }
}