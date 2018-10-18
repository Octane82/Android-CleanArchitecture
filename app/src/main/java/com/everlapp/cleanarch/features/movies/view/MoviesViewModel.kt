package com.everlapp.cleanarch.features.movies.view

import androidx.lifecycle.MutableLiveData
import com.everlapp.cleanarch.core.interactor.UseCase
import com.everlapp.cleanarch.core.platform.BaseViewModel
import com.everlapp.cleanarch.features.movies.domain.GetMovies
import com.everlapp.cleanarch.features.movies.dto.Movie
import com.everlapp.cleanarch.features.movies.dto.MovieView
import javax.inject.Inject


class MoviesViewModel
@Inject constructor(private val getMovies: GetMovies) : BaseViewModel() {

    var movies: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadMovies() = getMovies(UseCase.None()) { it.either(::handleFailure, :: handleMovieList) }


    private fun handleMovieList(movies: List<Movie>) {
        this.movies.value = movies.map { MovieView(it.id, it.poster) }
    }

}