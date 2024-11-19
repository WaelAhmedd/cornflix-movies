package com.app.movies.core.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WatchlistViewModel : ViewModel() {
    private val _watchlist = MutableStateFlow<Set<Int>>(emptySet())
    val watchlist: StateFlow<Set<Int>> get() = _watchlist

    fun toggleWatchlist(movieId: Int) {
        _watchlist.value = if (_watchlist.value.contains(movieId)) {
            _watchlist.value - movieId
        } else {
            _watchlist.value + movieId
        }
    }
}
