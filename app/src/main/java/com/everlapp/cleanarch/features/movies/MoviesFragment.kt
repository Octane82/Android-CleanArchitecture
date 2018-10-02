package com.everlapp.cleanarch.features.movies

import android.os.Bundle
import com.everlapp.cleanarch.R
import com.everlapp.cleanarch.core.exception.Failure
import com.everlapp.cleanarch.core.extension.observe
import com.everlapp.cleanarch.core.extension.failure

import com.everlapp.cleanarch.core.extension.viewModel
import com.everlapp.cleanarch.core.navigation.Navigator
import com.everlapp.cleanarch.core.platform.BaseFragment
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var moviesAdapter: MoviesAdapter

    private lateinit var moviesViewModel: MoviesViewModel

    override fun layoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        moviesViewModel = viewModel(viewModelFactory) {
            observe(movies, ::renderMoviesList)
            failure(failure, ::handleFailure)
        }
    }




    private fun renderMoviesList(movies: List<MovieView>?) {
        moviesAdapter.collection = movies.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        // TODO: !!!!
        /*when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is ListNotAvailable -> renderFailure(R.string.failure_movies_list_unavailable)
        }*/
    }


}