package com.movietest.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.paging.compose.collectAsLazyPagingItems
import com.movietest.presentation.ui.movie_list.MovieViewModel
import com.movietest.presentation.ui.movie_list.components.MovieList
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : ComponentActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val movieViewModel: MovieViewModel by instance()

    private var connectionLiveData: ConnectionLiveData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionLiveData = ConnectionLiveData(this)
        setContent {
            val lazyMoviesItems = movieViewModel.moviesFlow.collectAsLazyPagingItems()
            MovieList(
                lazyMoviesItems = lazyMoviesItems,
                isInPortraitOrientation = isInPortraitOrientation()
            )
            connectionLiveData?.observe(this) { isNetworkAvailable ->
                if (isNetworkAvailable) {
                    lazyMoviesItems.retry()
                } else {
                    Toast.makeText(this, "Network is unavailable", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun isInPortraitOrientation(): Boolean {
        return when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                false
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                true
            }
            else -> {
                false
            }
        }
    }
}