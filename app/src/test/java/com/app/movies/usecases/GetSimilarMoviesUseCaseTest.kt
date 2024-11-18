package com.app.movies.usecases


import com.app.movies.core.data.models.Genre
import com.app.movies.core.data.models.Movie
import com.app.movies.core.data.models.MovieDetailsResponse
import com.app.movies.core.data.models.ProductionCompany
import com.app.movies.core.data.models.SimilarMoviesResponse
import com.app.movies.core.domain.repos.IMoviesRepo
import com.app.movies.features.moviedetails.domain.usecases.GetMovieDetailsUseCase
import com.app.movies.features.moviedetails.domain.usecases.GetSimilarMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi

class GetSimilarMoviesUseCaseTest {

    @Mock
    private lateinit var moviesRepo: IMoviesRepo

    private lateinit var getSimilarMoviesUseCase: GetSimilarMoviesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getSimilarMoviesUseCase = GetSimilarMoviesUseCase(moviesRepo)
    }

    @Test
    fun `should return similar movies successfully`() = runBlocking {
        // Arrange
        val mockResponse = SimilarMoviesResponse(
            page = 1,
            results = listOf(
                Movie(
                    id = 1,
                    title = "Mock Similar Movie",
                    overview = "This is a similar movie.",
                    releaseDate = "2022-01-01",
                    voteAverage = 7.5,
                    voteCount = 800,
                    posterPath = "/mockPoster.jpg",
                    backdropPath = "/mockBackdrop.jpg",
                    genreIds = listOf(1, 2),
                    popularity = 150.0,
                    adult = false,
                    originalLanguage = "en",
                    originalTitle = "Mock Similar Movie",
                    video = false
                )
            ),
            totalPages = 1,
            totalResults = 1
        )
        whenever(moviesRepo.getSimilarMovies(1)).thenReturn(flowOf(Result.success(mockResponse)))

        // Act
        val result = getSimilarMoviesUseCase(1).first()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.results?.size)
        assertEquals("Mock Similar Movie", result.getOrNull()?.results?.first()?.title)
    }

    @Test
    fun `should return failure when an exception occurs`() = runBlocking {
        // Arrange
        val errorMessage = "Unable to fetch similar movies"
        whenever(moviesRepo.getSimilarMovies(1)).thenReturn(flowOf(Result.failure(Exception(errorMessage))))

        // Act
        val result = getSimilarMoviesUseCase(1).first()

        // Assert
        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
}
