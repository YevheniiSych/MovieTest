package com.movietest.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movietest.common.ResponseResult
import com.movietest.common.successBody
import com.movietest.data.remote.dto.toMoviesResponse
import com.movietest.domain.model.Movie
import com.movietest.domain.repository.MovieRepository

class MoviesPagingSource(private val movieRepository: MovieRepository) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        val response = movieRepository.getPopularMovies("en-US", page)
        return if (response.isSuccess) {
            val moviesResponse = response.successBody().toMoviesResponse()
            val movies = moviesResponse.results ?: emptyList()
            val nextKey = page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(movies, prevKey, nextKey)
        } else {
            val failureResponse = response as ResponseResult.Failure
            LoadResult.Error(failureResponse.error)
        }
    }
}