package com.everlapp.cleanarch.features.movies

import com.everlapp.cleanarch.core.exception.Failure

class MovieFailure {
    class ListNotAvailable: Failure.FeatureFailure()
    class NonExistentMovie: Failure.FeatureFailure()
}