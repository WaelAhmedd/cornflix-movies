package com.app.movies.core.data.models

import com.google.gson.annotations.SerializedName

data class SearchMoviesResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<Movie>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)