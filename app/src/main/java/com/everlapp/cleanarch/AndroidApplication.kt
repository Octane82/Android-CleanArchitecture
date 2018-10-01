package com.everlapp.cleanarch

import android.app.Application
import com.everlapp.cleanarch.core.di.ApplicationComponent
import com.everlapp.cleanarch.core.di.ApplicationModule
import com.everlapp.cleanarch.core.di.DaggerApplicationComponent

class AndroidApplication : Application() {


    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
        this.initializeLeakDetection()
    }


    private fun injectMembers() = appComponent.inject(this)

    // TODO: Add later !!! (Memory leaks detection)
    private fun initializeLeakDetection() {

    }

}