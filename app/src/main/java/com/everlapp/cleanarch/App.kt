package com.everlapp.cleanarch

import android.app.Application
import com.everlapp.cleanarch.core.di.ApplicationComponent
import com.everlapp.cleanarch.core.di.ApplicationModule
import com.everlapp.cleanarch.core.di.DaggerApplicationComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import com.squareup.leakcanary.LeakCanary
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber

class App : Application() {


    companion object {
        lateinit var INSTANCE: App
            private set
    }

    private lateinit var cicerone: Cicerone<Router>


    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }


    override fun onCreate() {
        super.onCreate()
        this.initializeLeakDetection()

        INSTANCE = this

        this.injectMembers()
        this.initializeThreeTenABP()
        this.initializeCicerone()
        this.initializeTimber()
    }

    private fun initializeLeakDetection() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }


    private fun injectMembers() = appComponent.inject(this)


    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initializeThreeTenABP() {
        AndroidThreeTen.init(this);
    }


    private fun initializeCicerone() {
        cicerone = Cicerone.create()
    }

    fun getNavigatorHolder() = cicerone.navigatorHolder

    fun getRouter() = cicerone.router


}