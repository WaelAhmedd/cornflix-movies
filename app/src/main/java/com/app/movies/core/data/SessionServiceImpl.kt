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

    private val sharePref: SharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
    }


    companion object {
        const val SHARED_PREFERENCES_FILE = "app_pref"
    }
}

