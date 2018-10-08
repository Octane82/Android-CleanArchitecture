package com.everlapp.cleanarch.features.movies.dto

import com.everlapp.cleanarch.core.extension.empty

data class Movie(val id: Int, val poster: String) {

    companion object {
        fun empty() = Movie(0, String.empty())
    }
}