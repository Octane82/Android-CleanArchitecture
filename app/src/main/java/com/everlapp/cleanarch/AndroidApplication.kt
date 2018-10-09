package com.everlapp.cleanarch

import android.app.Application
import com.everlapp.cleanarch.core.di.ApplicationComponent
import com.everlapp.cleanarch.core.di.ApplicationModule
import com.everlapp.cleanarch.core.di.DaggerApplicationComponent
import timber.log.Timber

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
        this.initializeTimber()
    }


    private fun injectMembers() = appComponent.inject(this)



    // TODO: Add later !!! (Memory leaks detection)
    private fun initializeLeakDetection() {

    }


    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}