package com.everlapp.cleanarch.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.everlapp.cleanarch.features.movies.view.MoviesViewModel
import com.everlapp.cleanarch.features.tasks.view.TasksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindsMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    abstract fun bindsTasksViewModel(tasksViewModel: TasksViewModel): ViewModel



    /*@Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindsMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel*/
}