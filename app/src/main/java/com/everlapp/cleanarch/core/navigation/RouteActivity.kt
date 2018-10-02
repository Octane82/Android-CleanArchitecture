package com.everlapp.cleanarch.core.navigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.everlapp.cleanarch.AndroidApplication
import com.everlapp.cleanarch.core.di.ApplicationComponent
import javax.inject.Inject

class RouteActivity : AppCompatActivity() {

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as AndroidApplication).appComponent
    }

    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        navigator.showMain(this)
    }
}