package com.movietest.presentation.ui.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.movietest.data.remote.MoviesPagingSource

class MovieViewModel(private val moviesSource: MoviesPagingSource) : ViewModel() {

    val moviesFlow = Pager(
        PagingConfig(pageSize = 1)
    ) {
        moviesSource
    }.flow.cachedIn(viewModelScope)


}
