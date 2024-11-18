package com.app.movies.core.utils

import com.app.movies.BuildConfig

object ImageUtils {

    fun constructPosterUrl(posterPath: String?): String {
        return if (!posterPath.isNullOrEmpty()) {
            "https://image.tmdb.org/t/p/w500$posterPath"
        } else {
            ""
        }
    }
}
