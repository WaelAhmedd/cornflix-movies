package com.app.movies.datasources

import com.app.movies.core.data.datasources.MoviesApiService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: MoviesApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getPopularMovies returns successful response`(): Unit = runBlocking {
        // Mock API response
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
                {
                  "page": 1,
                  "results": [
                    {
                      "id": 1,
                      "title": "Test Movie",
                      "overview": "This is a test movie",
                      "release_date": "2024-01-01",
                      "vote_average": 7.5,
                      "vote_count": 100,
                      "poster_path": "/test.jpg",
                      "backdrop_path": "/test_back.jpg",
                      "genre_ids": [28, 12],
                      "popularity": 10.0,
                      "adult": false,
                      "original_language": "en",
                      "original_title": "Test Movie",
                      "video": false
                    }
                  ],
                  "total_pages": 1,
                  "total_results": 1
                }
                """
            )
        mockWebServer.enqueue(mockResponse)

        // Make the API call
        val response = apiService.getPopularMovies()

        // Verify the response
        assertNotNull(response.body())
        assertEquals(1, response.body()?.results?.size)
        assertEquals("Test Movie", response.body()?.results?.first()?.title)
        response.body()?.results?.first()?.voteAverage?.let { assertEquals(7.5, it, 0.0) }
    }

    @Test
    fun `getPopularMovies returns error response`() = runBlocking {
        // Mock API error response
        val mockResponse = MockResponse()
            .setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        // Make the API call
        val response = apiService.getPopularMovies()

        // Verify the response
        assertEquals(404, response.code())
        assertNotNull(response.errorBody())
    }


    @Test
    fun `searchMovies returns successful response with no results`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
            {
              "page": 1,
              "results": [],
              "total_pages": 1,
              "total_results": 0
            }
            """
            )
        mockWebServer.enqueue(mockResponse)

        val response = apiService.searchMovies(query = "NonExistentMovie")

        assertNotNull(response.body())
        assertEquals(0, response.body()?.results?.size)
        assertEquals(1, response.body()?.page)
        assertEquals(0, response.body()?.totalResults)
    }
    @Test
    fun `searchMovies returns successful response with results`(): Unit = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
            {
              "page": 1,
              "results": [
                {
                  "id": 101,
                  "title": "Search Test Movie",
                  "overview": "A test movie returned by search.",
                  "release_date": "2024-03-15",
                  "vote_average": 8.5,
                  "vote_count": 1200,
                  "poster_path": "/test_poster.jpg",
                  "backdrop_path": "/test_backdrop.jpg",
                  "genre_ids": [18, 10749],
                  "popularity": 123.45,
                  "adult": false,
                  "original_language": "en",
                  "original_title": "Search Test Movie",
                  "video": false
                }
              ],
              "total_pages": 1,
              "total_results": 1
            }
            """
            )
        mockWebServer.enqueue(mockResponse)

        val response = apiService.searchMovies(query = "Test")

        assertNotNull(response.body())
        assertEquals(1, response.body()?.results?.size)
        assertEquals("Search Test Movie", response.body()?.results?.first()?.title)
        response.body()?.results?.first()?.voteAverage?.let { assertEquals(8.5, it, 0.0) }
    }
    @Test
    fun `searchMovies returns 404 error`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        val response = apiService.searchMovies(query = "Test")

        assertEquals(404, response.code())
        assertNotNull(response.errorBody())
    }


}
