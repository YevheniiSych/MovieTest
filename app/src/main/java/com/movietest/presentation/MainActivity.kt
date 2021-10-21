package com.movietest.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.movietest.presentation.ui.movie_list.MovieViewModel
import com.movietest.presentation.ui.movie_list.components.MovieList
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : ComponentActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val movieViewModel: MovieViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            setContent {
                MovieList(
                    moviesFlow = movieViewModel.moviesFlow,
                    isInPortraitOrientation = isInPortraitOrientation()
                )
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