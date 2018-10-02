package com.everlapp.cleanarch.features.movies

import android.content.Context
import android.content.Intent
import com.everlapp.cleanarch.core.platform.BaseActivity
import com.everlapp.cleanarch.core.platform.BaseFragment

class MoviesActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }

    override fun fragment(): BaseFragment = MoviesFragment()

}