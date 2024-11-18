package com.app.movies.usecases

import com.app.movies.core.data.models.PopularMoviesResponse
import com.app.movies.core.domain.repos.IMoviesRepo
import com.app.movies.features.home.domain.usecases.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetPopularMoviesUseCaseTest {

    @Mock
    private lateinit var moviesRepo: IMoviesRepo
    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getPopularMoviesUseCase = GetPopularMoviesUseCase(moviesRepo)
    }

    @Test
    fun `invoke returns successful response`() = runBlockingTest {
        // Mock data
        val mockResponse = PopularMoviesResponse(
            page = 1,
            results = listOf(),
            totalPages = 1,
            totalResults = 1
        )
        `when`(moviesRepo.getPopularMovies("en-US", 1)).thenReturn(flow {
            emit(Result.success(mockResponse))
        })

        // Execute
        val result = getPopularMoviesUseCase.invoke("en-US", 1)

        // Verify
        result.collect {
            assert(it.isSuccess)
            assertEquals(mockResponse, it.getOrNull())
        }
    }

    @Test
    fun `invoke returns error response`() = runBlockingTest {
        // Mock error
        val mockError = Exception("Network Error")
        `when`(moviesRepo.getPopularMovies("en-US", 1)).thenReturn(flow {
            emit(Result.failure(mockError))
        })

        // Execute
        val result = getPopularMoviesUseCase.invoke("en-US", 1)

        // Verify
        result.collect {
            assert(it.isFailure)
            assertEquals(mockError.message, it.exceptionOrNull()?.message)
        }
    }
}
