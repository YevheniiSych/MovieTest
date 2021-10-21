package com.movietest.data.remote

import com.movietest.data.remote.dto.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String?,
        @Query("page") page: Int?,
        @Query("region") region: String?
    ): MoviesResponseDto
}