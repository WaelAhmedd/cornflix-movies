package com.app.movies.repoimpl

import com.app.movies.core.data.datasources.MoviesApiService
import com.app.movies.core.data.models.*
import com.app.movies.core.data.reposimp.MoviesRepoImpl
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
}
