package com.app.movies.mocks

import com.app.movies.core.data.models.BelongsToCollection
import com.app.movies.core.data.models.Genre
import com.app.movies.core.data.models.Movie
import com.app.movies.core.data.models.MovieDetailsResponse
import com.app.movies.core.data.models.ProductionCompany
import com.app.movies.core.data.models.SimilarMoviesResponse


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


    fun getMockMovieDetails(): MovieDetailsResponse {
        return MovieDetailsResponse(
            id = 3,
            title = "Mock Movie Details",
            overview = "A detailed overview of the mock movie.",
            releaseDate = "2024-07-01",
            voteAverage = 8.5,
            voteCount = 1200,
            posterPath = "/mock_movie_poster.jpg",
            backdropPath = "/mock_movie_backdrop.jpg",
            genres = listOf(
                Genre(id = 28, name = "Action"),
                Genre(id = 12, name = "Adventure")
            ),
            runtime = 130,
            budget = 200000000,
            revenue = 600000000,
            status = "Released",
            tagline = "A thrilling mock movie experience.",
            homepage = "https://mockmovie.example.com",
            productionCompanies = listOf(
                ProductionCompany(
                    id = 1,
                    name = "Mock Production Company",
                    logoPath = "/mock_logo.jpg",
                    originCountry = "US"
                )
            ),
            belongsToCollection = BelongsToCollection(
                id = 10,
                name = "Mock Movie Collection",
                posterPath = "/mock_collection_poster.jpg",
                backdropPath = "/mock_collection_backdrop.jpg"
            )
        )
    }
    fun getMockSimilarMovies(): SimilarMoviesResponse {
        return SimilarMoviesResponse(
            page = 1,
            results = listOf(
                Movie(
                    id = 101,
                    title = "Similar Movie 1",
                    overview = "Overview of the first similar movie.",
                    releaseDate = "2024-06-15",
                    voteAverage = 7.2,
                    voteCount = 500,
                    posterPath = "/similar_movie_1_poster.jpg",
                    backdropPath = "/similar_movie_1_backdrop.jpg",
                    genreIds = listOf(28, 12),
                    popularity = 95.4,
                    adult = false,
                    originalLanguage = "en",
                    originalTitle = "Similar Movie 1",
                    video = false
                ),
                Movie(
                    id = 102,
                    title = "Similar Movie 2",
                    overview = "Overview of the second similar movie.",
                    releaseDate = "2024-07-20",
                    voteAverage = 8.0,
                    voteCount = 800,
                    posterPath = "/similar_movie_2_poster.jpg",
                    backdropPath = "/similar_movie_2_backdrop.jpg",
                    genreIds = listOf(16, 35),
                    popularity = 120.7,
                    adult = false,
                    originalLanguage = "en",
                    originalTitle = "Similar Movie 2",
                    video = false
                )
            ),
            totalPages = 1,
            totalResults = 2
        )
    }
}
