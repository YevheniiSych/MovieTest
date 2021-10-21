package com.movietest

import android.app.Application
import com.movietest.data.remote.MoviesPagingSource
import com.movietest.data.repository.MovieRepositoryImpl
import com.movietest.domain.repository.MovieRepository
import com.movietest.presentation.ui.movie_list.MovieViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class MovieApp() : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MovieApp))
        bind<MovieRepository>() with singleton { MovieRepositoryImpl() }
        bind() from singleton { MoviesPagingSource(instance()) }
        bind() from provider { MovieViewModel(instance()) }
    }
}