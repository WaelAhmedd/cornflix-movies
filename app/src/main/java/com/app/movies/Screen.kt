package com.app.movies

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}