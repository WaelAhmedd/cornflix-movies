package com.app.movies.mocks

import com.app.movies.core.data.models.Movie


object MocksMovies {

    fun getMockMoviesList(): List<Movie> {
        return listOf(
            Movie(
                id = 1,
                title = "Mock Movie 1",
                overview = "This is a mock overview for movie 1.",
                releaseDate = "2024-01-01",
                voteAverage = 7.5,
                voteCount = 1000,
                posterPath = "/mock_poster_1.jpg",
                backdropPath = "/mock_backdrop_1.jpg",
                genreIds = listOf(28, 12),
                popularity = 100.0,
                adult = false,
                originalLanguage = "en",
                originalTitle = "Mock Movie 1",
                video = false
            ),
            Movie(
                id = 2,
                title = "Mock Movie 2",
                overview = "This is a mock overview for movie 2.",
                releaseDate = "2024-02-01",
                voteAverage = 8.0,
                voteCount = 1500,
                posterPath = "/mock_poster_2.jpg",
                backdropPath = "/mock_backdrop_2.jpg",
                genreIds = listOf(16, 35),
                popularity = 150.0,
                adult = false,
                originalLanguage = "en",
                originalTitle = "Mock Movie 2",
                video = false
            ),
            Movie(
                id = 3,
                title = "Mock Movie 3",
                overview = "This is a mock overview for movie 3.",
                releaseDate = "2024-03-01",
                voteAverage = 6.8,
                voteCount = 500,
                posterPath = "/mock_poster_3.jpg",
                backdropPath = "/mock_backdrop_3.jpg",
                genreIds = listOf(18, 10749),
                popularity = 80.0,
                adult = false,
                originalLanguage = "en",
                originalTitle = "Mock Movie 3",
                video = false
            ),
            Movie(
                id = 4,
                title = "Mock Movie 4",
                overview = "This is a mock overview for movie 4.",
                releaseDate = "2024-04-01",
                voteAverage = 9.0,
                voteCount = 2000,
                posterPath = "/mock_poster_4.jpg",
                backdropPath = "/mock_backdrop_4.jpg",
                genreIds = listOf(28, 14),
                popularity = 250.0,
                adult = false,
                originalLanguage = "en",
                originalTitle = "Mock Movie 4",
                video = false
            )
        )
    }
}
