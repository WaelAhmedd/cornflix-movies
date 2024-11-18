package com.app.movies.repoimpl

import com.app.movies.core.data.datasources.MoviesApiService
import com.app.movies.core.data.models.*
import com.app.movies.core.data.reposimp.MoviesRepoImpl
import com.app.movies.mocks.MocksMovies
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
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
}
