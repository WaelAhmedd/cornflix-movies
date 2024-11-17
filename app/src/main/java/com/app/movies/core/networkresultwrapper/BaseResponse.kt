package com.app.movies.core.networkresultwrapper

data class BaseResponse<T>(
    val data: T?,
    val message: String?
)