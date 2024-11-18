package com.app.movies.datasources

import com.app.movies.core.data.datasources.MoviesApiService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
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


    @Test
    fun `getMovieDetails returns successful response`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
            {
              "id": 101,
              "title": "Test Movie Details",
              "overview": "Detailed overview of the test movie.",
              "release_date": "2024-05-10",
              "vote_average": 8.2,
              "vote_count": 1500,
              "poster_path": "/test_poster.jpg",
              "backdrop_path": "/test_backdrop.jpg",
              "genres": [
                { "id": 28, "name": "Action" },
                { "id": 12, "name": "Adventure" }
              ],
              "runtime": 120,
              "budget": 200000000,
              "revenue": 500000000,
              "status": "Released",
              "tagline": "The best test movie ever!",
              "homepage": "https://example.com",
              "production_companies": [
                {
                  "id": 1,
                  "name": "Test Production",
                  "logo_path": "/test_logo.jpg",
                  "origin_country": "US"
                }
              ]
            }
            """
            )
        mockWebServer.enqueue(mockResponse)

        val response = apiService.getMovieDetails(movieId = 101)

        assertNotNull(response.body())
        assertEquals("Test Movie Details", response.body()?.title)
        response.body()?.voteAverage?.let { assertEquals(8.2, it, 0.0) }
        assertEquals(2, response.body()?.genres?.size)
        assertEquals("Action", response.body()?.genres?.first()?.name)
        assertEquals(120, response.body()?.runtime)
    }
    @Test
    fun `getMovieDetails returns 404 error`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        val response = apiService.getMovieDetails(movieId = 999999)

        assertEquals(404, response.code())
        assertNotNull(response.errorBody())
    }
    @Test
    fun `getMovieDetails handles missing fields in response`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
            {
              "id": 102,
              "title": "",
              "overview": null,
              "release_date": "",
              "vote_average": 0.0,
              "vote_count": 0,
              "poster_path": null,
              "backdrop_path": null,
              "genres": [],
              "runtime": null,
              "budget": null,
              "revenue": null,
              "status": "Unknown",
              "tagline": null,
              "homepage": null,
              "production_companies": []
            }
            """
            )
        mockWebServer.enqueue(mockResponse)

        val response = apiService.getMovieDetails(movieId = 102)

        assertNotNull(response.body())
        val movie = response.body()
        assertEquals("", movie?.title)
        assertEquals("Unknown", movie?.status)
        assertTrue(movie?.genres?.isEmpty() == true)
        assertNull(movie?.runtime)
        assertNull(movie?.budget)
    }
    @Test
    fun `getMovieDetails returns error for invalid movie ID`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(400)
            .setBody(
                """
            {
              "status_code": 34,
              "status_message": "The resource you requested could not be found."
            }
            """
            )
        mockWebServer.enqueue(mockResponse)

        val response = apiService.getMovieDetails(movieId = -1)

        assertEquals(400, response.code())
        assertNotNull(response.errorBody())
    }




}
