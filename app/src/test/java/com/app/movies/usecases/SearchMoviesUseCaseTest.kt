package com.app.movies.usecases


import com.app.movies.core.data.models.SearchMoviesResponse
import com.app.movies.core.domain.repos.IMoviesRepo
import com.app.movies.features.home.domain.usecases.SearchMoviesUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchMoviesUseCaseTest {

    @Mock
    private lateinit var moviesRepo: IMoviesRepo
    private lateinit var searchMoviesUseCase: SearchMoviesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        searchMoviesUseCase = SearchMoviesUseCase(moviesRepo)
    }

    @Test
    fun `invoke returns successful response`() = runBlockingTest {
        // Mock data
        val mockResponse = SearchMoviesResponse(
            page = 1,
            results = listOf(),
            totalPages = 1,
            totalResults = 1
        )
        `when`(moviesRepo.searchMovies("test", 1)).thenReturn(flow {
            emit(Result.success(mockResponse))
        })

        // Execute
        val result = searchMoviesUseCase.invoke("test", 1)

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
        `when`(moviesRepo.searchMovies("test", 1)).thenReturn(flow {
            emit(Result.failure(mockError))
        })

        // Execute
        val result = searchMoviesUseCase.invoke("test", 1)

        // Verify
        result.collect {
            assert(it.isFailure)
            assertEquals(mockError.message, it.exceptionOrNull()?.message)
        }
    }
}
