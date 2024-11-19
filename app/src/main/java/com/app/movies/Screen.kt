package com.app.movies

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home :Screen("home")
    object MovieDetails :Screen("moviedetails")

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}