package com.everlapp.cleanarch.features.tasks.view

import android.content.Context
import android.content.Intent
import com.everlapp.cleanarch.core.platform.BaseActivity
import com.everlapp.cleanarch.core.platform.BaseFragment

class TaskDetailsActivity : BaseActivity() {

    companion object {

        fun callingIntent(context: Context) : Intent {
            val intent = Intent(context, TaskDetailsActivity::class.java)
            // put extra if needed
            return intent
        }

    }


    override fun fragment(): BaseFragment = TaskDetailsFragment()
}