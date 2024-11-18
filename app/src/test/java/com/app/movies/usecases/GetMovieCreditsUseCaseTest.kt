package com.app.movies.usecases
import com.app.movies.core.data.models.CastMember
import com.app.movies.core.data.models.CrewMember
import com.app.movies.core.data.models.Genre
import com.app.movies.core.data.models.MovieCreditsResponse
import com.app.movies.core.data.models.MovieDetailsResponse
import com.app.movies.core.data.models.ProductionCompany
import com.app.movies.core.domain.repos.IMoviesRepo
import com.app.movies.features.moviedetails.domain.usecases.GetMovieCreditsUseCase
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
class GetMovieCreditsUseCaseTest {

    @Mock
    private lateinit var moviesRepo: IMoviesRepo

    private lateinit var getMovieCreditsUseCase: GetMovieCreditsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getMovieCreditsUseCase = GetMovieCreditsUseCase(moviesRepo)
    }

    @Test
    fun `should return movie credits successfully`() = runBlocking {
        // Arrange
        val mockResponse = MovieCreditsResponse(
            id = 1,
            cast = listOf(
                CastMember(
                    adult = false,
                    gender = 2,
                    id = 100,
                    knownForDepartment = "Acting",
                    name = "Mock Actor",
                    originalName = "Mock Actor",
                    popularity = 100.0,
                    profilePath = "/mockActor.jpg",
                    castId = 10,
                    character = "Mock Character",
                    creditId = "mockCreditId",
                    order = 1
                )
            ),
            crew = listOf(
                CrewMember(
                    adult = false,
                    gender = 2,
                    id = 200,
                    knownForDepartment = "Directing",
                    name = "Mock Director",
                    originalName = "Mock Director",
                    popularity = 50.0,
                    profilePath = "/mockDirector.jpg",
                    creditId = "mockCreditId",
                    department = "Directing",
                    job = "Director"
                )
            )
        )
        whenever(moviesRepo.getMovieCredits(1)).thenReturn(flowOf(Result.success(mockResponse)))

        // Act
        val result = getMovieCreditsUseCase(1).first()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.id)
        assertEquals("Mock Actor", result.getOrNull()?.cast?.first()?.name)
        assertEquals("Mock Director", result.getOrNull()?.crew?.first()?.name)
    }

    @Test
    fun `should return failure when an exception occurs`() = runBlocking {
        // Arrange
        val errorMessage = "Unable to fetch movie credits"
        whenever(moviesRepo.getMovieCredits(1)).thenReturn(flowOf(Result.failure(Exception(errorMessage))))

        // Act
        val result = getMovieCreditsUseCase(1).first()

        // Assert
        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
}
