package com.everlapp.cleanarch.features.movies

import com.everlapp.cleanarch.core.exception.Failure
import com.everlapp.cleanarch.core.functional.Either

interface MoviesRepository {

    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>

}