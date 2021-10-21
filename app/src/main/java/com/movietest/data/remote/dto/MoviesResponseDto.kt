package com.movietest.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.movietest.domain.model.MoviesResponse

data class MoviesResponseDto(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MovieDto>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)

fun MoviesResponseDto.toMoviesResponse(): MoviesResponse {

    val moviesList = results?.map { it?.toMovie() }

    return MoviesResponse(
        page = page,
        results = moviesList,
        totalPages = totalPages,
        totalResults = totalResults
    )
}