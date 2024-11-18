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

    @Test
    fun `getMovieDetails returns successful response`() = runBlockingTest {
        // Mock data
        val mockResponse = MocksMovies.getMockMovieDetails()

        `when`(apiService.getMovieDetails(3)).thenReturn(Response.success(mockResponse))

        // Execute
        val results = moviesRepo.getMovieDetails(movieId = 3).toList()

        // Verify
        assertEquals(Result.success(mockResponse), results.last())
        assertEquals("Mock Movie Details", results.last().getOrThrow().title)
    }

    @Test
    fun `getMovieDetails returns error response`() = runBlockingTest {
        // Mock error response
        `when`(apiService.getMovieDetails(999)).thenReturn(
            Response.error(404, okhttp3.ResponseBody.create(null, "Not Found"))
        )

        // Execute
        val results = moviesRepo.getMovieDetails(movieId = 999).toList()

        // Verify
        assert(results.last().isFailure)
    }

    @Test
    fun `getSimilarMovies returns successful response with results`() = runBlockingTest {
        // Mock data
        val mockResponse = SimilarMoviesResponse(
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

        // Mock API response
        `when`(apiService.getSimilarMovies(101)).thenReturn(Response.success(mockResponse))

        // Execute
        val results = moviesRepo.getSimilarMovies(movieId = 101).toList()

        // Verify
        assertEquals(Result.success(mockResponse), results.last())
        assertEquals(2, results.last().getOrThrow().results.size)
        assertEquals("Similar Movie 1", results.last().getOrThrow().results[0].title)
    }

    @Test
    fun `getSimilarMovies returns successful response with no results`() = runBlockingTest {
        // Mock data
        val mockResponse = SimilarMoviesResponse(
            page = 1,
            results = emptyList(),
            totalPages = 1,
            totalResults = 0
        )

        // Mock API response
        `when`(apiService.getSimilarMovies(999)).thenReturn(Response.success(mockResponse))

        // Execute
        val results = moviesRepo.getSimilarMovies(movieId = 999).toList()

        // Verify
        assertEquals(Result.success(mockResponse), results.last())
        assertTrue(results.last().getOrThrow().results.isEmpty())
    }

    @Test
    fun `getSimilarMovies returns error response`() = runBlockingTest {
        // Mock API response
        `when`(apiService.getSimilarMovies(999)).thenReturn(
            Response.error(404, okhttp3.ResponseBody.create(null, "Not Found"))
        )

        // Execute
        val results = moviesRepo.getSimilarMovies(movieId = 999).toList()

        // Verify
        assert(results.last().isFailure)
    }

    @Test
    fun `getSimilarMovies handles exception`() = runBlockingTest {
        // Mock API exception
        `when`(apiService.getSimilarMovies(101)).thenThrow(RuntimeException("Network error"))

        // Execute
        val results = moviesRepo.getSimilarMovies(movieId = 101).toList()

        // Verify
        assert(results.last().isFailure)
        assertEquals("Network error", (results.last().exceptionOrNull() as RuntimeException).message)
    }


}
