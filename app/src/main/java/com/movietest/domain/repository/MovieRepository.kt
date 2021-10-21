package com.movietest.domain.repository

import com.movietest.common.ResponseResult
import com.movietest.data.remote.dto.MoviesResponseDto
import retrofit2.Response
import retrofit2.http.Query

interface MovieRepository {
    suspend fun getPopularMovies(
        language: String? = null,
        page: Int? = null,
        region: String? = null
    ): ResponseResult<MoviesResponseDto>
}