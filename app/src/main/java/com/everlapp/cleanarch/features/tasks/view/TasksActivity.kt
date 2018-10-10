package com.everlapp.cleanarch.features.tasks.view

import android.content.Context
import android.content.Intent
import com.everlapp.cleanarch.core.platform.BaseActivity
import com.everlapp.cleanarch.core.platform.BaseFragment

class TasksActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, TasksActivity::class.java)
    }

    override fun fragment(): BaseFragment = TasksFragment()

}