package com.everlapp.cleanarch.core.di

import android.content.Context
import com.everlapp.cleanarch.AndroidApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext() : Context = application


/*
    todo !!!
    @Provides
    @Singleton
    fun provideMoviesRepository() {

    }
*/

}