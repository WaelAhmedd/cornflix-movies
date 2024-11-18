package com.app.movies.core.domain

interface SessionService {
    fun addMovieToWatchlist(movieId: Int)
    fun removeMovieFromWatchlist(movieId: Int)
    fun getWatchlist(): Set<Int>
}