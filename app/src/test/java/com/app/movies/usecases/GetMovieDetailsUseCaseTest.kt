package com.app.movies.usecases

import com.app.movies.core.data.models.Genre
import com.app.movies.core.data.models.MovieDetailsResponse
import com.app.movies.core.data.models.ProductionCompany
import com.app.movies.core.domain.repos.IMoviesRepo
import com.app.movies.features.moviedetails.domain.usecases.GetMovieDetailsUseCase
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
class GetMovieDetailsUseCaseTest {

    @Mock
    private lateinit var moviesRepo: IMoviesRepo

    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getMovieDetailsUseCase = GetMovieDetailsUseCase(moviesRepo)
    }

    @Test
    fun `should return movie details successfully`() = runBlocking {
        // Arrange
        val mockResponse = MovieDetailsResponse(
            id = 1,
            title = "Mock Movie",
            overview = "This is a mock overview",
            releaseDate = "2022-01-01",
            voteAverage = 8.5,
            voteCount = 1200,
            posterPath = "/mockPoster.jpg",
            backdropPath = "/mockBackdrop.jpg",
            genres = listOf(Genre(1, "Action")),
            runtime = 120,
            budget = 100000000,
            revenue = 200000000,
            status = "Released",
            tagline = "Mock Tagline",
            homepage = "https://example.com",
            productionCompanies = listOf(
                ProductionCompany(1, "Mock Studio", "/mockLogo.png", "US")
            ),
            belongsToCollection = null
        )
        whenever(moviesRepo.getMovieDetails(1)).thenReturn(flowOf(Result.success(mockResponse)))

        // Act
        val result = getMovieDetailsUseCase(1).first()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals("Mock Movie", result.getOrNull()?.title)
        assertEquals(1, result.getOrNull()?.id)
    }

    @Test
    fun `should return failure when an exception occurs`() = runBlocking {
        // Arrange
        val errorMessage = "Unable to fetch movie details"
        whenever(moviesRepo.getMovieDetails(1)).thenReturn(flowOf(Result.failure(Exception(errorMessage))))

        // Act
        val result = getMovieDetailsUseCase(1).first()

        // Assert
        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
}
