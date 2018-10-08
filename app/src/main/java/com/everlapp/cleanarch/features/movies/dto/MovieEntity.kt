package com.everlapp.cleanarch.features.movies.dto

import com.everlapp.cleanarch.features.movies.dto.Movie

data class MovieEntity(private val id: Int, private val poster: String) {
    fun toMovie() = Movie(id, poster)
}