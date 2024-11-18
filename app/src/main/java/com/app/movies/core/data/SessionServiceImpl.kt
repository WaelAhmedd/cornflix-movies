package com.app.movies.core.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.app.movies.core.domain.SessionService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : SessionService {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
    }


    companion object {
        const val SHARED_PREFERENCES_FILE = "app_pref"
        private const val WATCHLIST_KEY = "watchlist"
    }

    override fun addMovieToWatchlist(movieId: Int) {
        val watchlist = getWatchlist().toMutableSet()
        watchlist.add(movieId)
        sharedPreferences.edit()
            .putStringSet(WATCHLIST_KEY, watchlist.map { it.toString() }.toSet()).apply()

    }

    override fun removeMovieFromWatchlist(movieId: Int) {
        val watchlist = getWatchlist().toMutableSet()
        watchlist.remove(movieId)
        sharedPreferences.edit()
            .putStringSet(WATCHLIST_KEY, watchlist.map { it.toString() }.toSet()).apply()
    }

    override fun getWatchlist(): Set<Int> {
        return sharedPreferences.getStringSet(WATCHLIST_KEY, emptySet())
            ?.map { it.toInt() }?.toSet() ?: emptySet()
    }
}

