package com.movietest.presentation.ui.movie_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.movietest.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieList(moviesFlow: Flow<PagingData<Movie>>) {
    val lazyMoviesItems = moviesFlow.collectAsLazyPagingItems()
    LazyVerticalGrid(cells = GridCells.Fixed(2) ) {
        items(lazyMoviesItems.itemCount){ i ->
            lazyMoviesItems[i]?.let { movie ->
                MovieItem(movie = movie)
            }
        }
    }
}