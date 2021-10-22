package com.movietest.data.repository

import android.util.Log
import com.movietest.common.Constants
import com.movietest.common.Constants.API_KEY
import com.movietest.common.ResponseResult
import com.movietest.data.remote.MovieApi
import com.movietest.data.remote.dto.MoviesResponseDto
import com.movietest.domain.repository.MovieRepository
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepositoryImpl : MovieRepository {

    private val movieApi: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    override suspend fun getPopularMovies(
        language: String?,
        page: Int?,
        region: String?
    ): ResponseResult<MoviesResponseDto> {
        return try {
            val resp = movieApi.getPopularMovies(API_KEY, language, page, region)
            ResponseResult.Success(resp)
        } catch (ex: Exception) {
            ResponseResult.Failure(ex.message ?: "", ex.fillInStackTrace())
        }
    }
}