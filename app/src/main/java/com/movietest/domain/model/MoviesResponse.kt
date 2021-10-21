package com.movietest.domain.model

data class MoviesResponse(
    val page: Int?,
    val results: List<Movie>?,
    val totalPages: Int?,
    val totalResults: Int?
)