
 <img src="https://github.com/user-attachments/assets/b57fa8f3-a2f9-4287-827e-abd11a42abe5" alt="Cornflix Logo" width="200" height="200">


## Overview

The **Cornflix App** is a modern and feature-rich application designed to showcase movies. It provides users with the ability to browse popular movies, search for specific titles, and view detailed information about selected movies. The app focuses on performance, scalability, and a clean design using modern Android development best practices.

---







## Features

### Home Screen
- **Search Movies**: Users can search for movies by title.
- **Popular Movies**: Displays a list of popular movies for users to explore.
- **Add to Watchlist**: Users can toggle movies to/from their watchlist.

### Movie Details Screen
- Displays detailed information about a movie:
    - Title, overview, image, tagline, revenue, release date, status.
    - Add to watchlist functionality.
- **Similar Movies**: Shows a list of up to 5 similar movies.
- **Cast and Crew**:
    - Displays the top 5 most popular actors and directors grouped by departments.

---

## Architecture

The Movies App follows **Clean Architecture** principles, ensuring separation of concerns and scalability.

### Layers:
1. **Presentation Layer**:
    - Built with Jetpack Compose for modern, declarative UI.
    - Uses `ViewModel` with `StateFlow` to handle UI states and manage data reactivity.
    - States and events are handled via a `BaseViewModel` for consistency and code reuse.

2. **Domain Layer**:
    - Contains **use cases**, encapsulating business logic and providing a clear API to the presentation layer.
    - Ensures a single responsibility for each use case.

3. **Data Layer**:
    - Contains a single **Repository** for managing data sources.
    - The repository fetches data from remote APIs using Retrofit and caches states locally when required.

### Why a Single Repository?
We used one repository (`MoviesRepoImpl`) for simplicity and efficiency since the app's data sources are all related to movies (e.g., popular movies, movie details, similar movies, credits). This avoids unnecessary complexity in managing multiple repositories while maintaining a clean, centralized data flow. For larger apps, repositories could be split if data sources become more complex or varied.

---

## Tech Stack

- **Kotlin**: Language of choice for modern Android development.
- **Jetpack Compose**: For building a declarative and reactive UI.
- **StateFlow**: Handles reactive data flow between the ViewModel and UI.
- **Dagger Hilt**: Dependency injection framework to simplify and modularize dependency management.
- **Retrofit**: REST client for API calls.
- **Coil**: For efficient image loading and caching.
- **SharedPreferences**: Stores the user's watchlist persistently.

---

## How Features Are Implemented

### Home Screen
- **Search**: Fetches search results via `SearchMoviesUseCase` and updates the UI with the results. If no results are found, it falls back to displaying popular movies.
- **Popular Movies**: Uses `GetPopularMoviesUseCase` to fetch popular movies when the screen is loaded.
- **Pagination**: Supports lazy loading for both search and popular movie lists, fetching new pages when the user scrolls.

### Movie Details Screen
- Combines multiple use cases:
    - `GetMovieDetailsUseCase`: Fetches detailed information about the selected movie.
    - `GetSimilarMoviesUseCase`: Fetches similar movies for recommendations.
    - `GetMovieCreditsUseCase`: Fetches cast and crew details.
- All data is loaded concurrently using `combine` from Kotlin's Coroutines Flow API to optimize performance.

---

