package com.app.movies.repoimpl

import com.app.movies.core.data.datasources.MoviesApiService
import com.app.movies.core.data.models.*
import com.app.movies.core.data.reposimp.MoviesRepoImpl
import com.app.movies.mocks.MocksMovies
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MoviesRepoImplTest {

    @Mock
    private lateinit var apiService: MoviesApiService
    private lateinit var moviesRepo: MoviesRepoImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        moviesRepo = MoviesRepoImpl(apiService)
    }

    @Test
    fun `getPopularMovies returns successful response`() = runBlockingTest {
        val mockResponse = PopularMoviesResponse(
            page = 1,
            results = MocksMovies.getMockMoviesList(),
            totalPages = 1,
            totalResults = 1
        )
        `when`(apiService.getPopularMovies("en-US", 1)).thenReturn(Response.success(mockResponse))

        val results = moviesRepo.getPopularMovies(language = "en-US", page = 1).toList()

        assertEquals(Result.success(mockResponse), results.last())
    }

    @Test
    fun `getPopularMovies returns error response`() = runBlockingTest {
        `when`(apiService.getPopularMovies("en-US", 1)).thenReturn(Response.error(404, okhttp3.ResponseBody.create(null, "")))

        val results = moviesRepo.getPopularMovies(language = "en-US", page = 1).toList()

        assert(results.last().isFailure)
    }
    @Test
    fun `searchMovies returns successful response with results`() = runBlockingTest {
        // Mock data
        val mockResponse = SearchMoviesResponse(
            page = 1,
            results = listOf(
                Movie(
                    id = 2,
                    title = "Search Test Movie",
                    overview = "Overview of search test movie.",
                    releaseDate = "2024-06-01",
                    voteAverage = 8.0,
                    voteCount = 1500,
                    posterPath = "/search_test_poster.jpg",
                    backdropPath = "/search_test_backdrop.jpg",
                    genreIds = listOf(28, 12),
                    popularity = 120.5,
                    adult = false,
                    originalLanguage = "en",
                    originalTitle = "Search Test Movie",
                    video = false
                )
            ),
            totalPages = 1,
            totalResults = 1
        )

        `when`(apiService.searchMovies("test", 1)).thenReturn(Response.success(mockResponse))

        // Execute
        val results = moviesRepo.searchMovies(query = "test", page = 1).toList()

        // Verify
        assertEquals(Result.success(mockResponse), results.last())
    }

    @Test
    fun `searchMovies returns successful response with no results`() = runBlockingTest {
        // Mock data
        val mockResponse = SearchMoviesResponse(
            page = 1,
            results = emptyList(),
            totalPages = 1,
            totalResults = 0
        )

        `when`(apiService.searchMovies("nonexistent", 1)).thenReturn(Response.success(mockResponse))

        // Execute
        val results = moviesRepo.searchMovies(query = "nonexistent", page = 1).toList()

        // Verify
        assertEquals(Result.success(mockResponse), results.last())
        assertTrue(results.last().getOrThrow().results.isEmpty())
    }

    @Test
    fun `searchMovies returns error response`() = runBlockingTest {
        // Mock error response
        `when`(apiService.searchMovies("error", 1)).thenReturn(
            Response.error(500, okhttp3.ResponseBody.create(null, "Internal Server Error"))
        )

        // Execute
        val results = moviesRepo.searchMovies(query = "error", page = 1).toList()

        // Verify
        assert(results.last().isFailure)
    }

}
