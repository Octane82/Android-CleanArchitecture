package com.everlapp.cleanarch.core.di

import com.everlapp.cleanarch.App
import com.everlapp.cleanarch.core.di.viewmodel.ViewModelModule
import com.everlapp.cleanarch.core.navigation.RouteActivity
import com.everlapp.cleanarch.features.movies.view.MoviesFragment
import com.everlapp.cleanarch.features.tasks.view.TasksFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(application: App)
    fun inject(routeActivity: RouteActivity)


    fun inject(fragment: MoviesFragment)
    fun inject(fragment: TasksFragment)

}