package com.everlapp.cleanarch.features.movies

import android.arch.lifecycle.MutableLiveData
import com.everlapp.cleanarch.core.interactor.UseCase
import com.everlapp.cleanarch.core.platform.BaseViewModel
import javax.inject.Inject


class MoviesViewModel
@Inject constructor(private val getMovies: GetMovies) : BaseViewModel() {

    var movies: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadMovies() = getMovies(UseCase.None()) { it.either(::handleFailure, :: handleMovieList) }


    private fun handleMovieList(movies: List<Movie>) {
        this.movies.value = movies.map { MovieView(it.id, it.poster) }
    }

}