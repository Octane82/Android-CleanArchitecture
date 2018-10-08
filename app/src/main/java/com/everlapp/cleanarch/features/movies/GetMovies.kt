package com.everlapp.cleanarch.features.movies

import com.everlapp.cleanarch.core.exception.Failure
import com.everlapp.cleanarch.core.functional.Either
import com.everlapp.cleanarch.core.interactor.UseCase
import com.everlapp.cleanarch.features.movies.data.MoviesRepository
import com.everlapp.cleanarch.features.movies.dto.Movie
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, UseCase.None>() {


    override suspend fun run(params: None): Either<Failure, List<Movie>> = moviesRepository.movies()


}