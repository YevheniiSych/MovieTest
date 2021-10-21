package com.movietest.presentation

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
                MovieList(moviesFlow = movieViewModel.moviesFlow)
            }
        }
    }
}