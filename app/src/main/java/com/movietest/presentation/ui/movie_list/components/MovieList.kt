package com.movietest.presentation.ui.movie_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.movietest.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieList(lazyMoviesItems: LazyPagingItems<Movie>, isInPortraitOrientation: Boolean) {
    LazyVerticalGrid(cells = GridCells.Fixed(if (isInPortraitOrientation) 2 else 4)) {
        items(lazyMoviesItems.itemCount) { i ->
            lazyMoviesItems[i]?.let { movie ->
                MovieItem(movie = movie)
            }
        }


    }
}