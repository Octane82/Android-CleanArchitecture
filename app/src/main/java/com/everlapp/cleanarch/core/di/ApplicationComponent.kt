package com.everlapp.cleanarch.core.di

import com.everlapp.cleanarch.AndroidApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: AndroidApplication)

}