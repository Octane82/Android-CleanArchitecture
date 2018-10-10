package com.everlapp.cleanarch.features.tasks.view

import android.os.Bundle
import com.everlapp.cleanarch.R
import com.everlapp.cleanarch.core.platform.BaseFragment

class TasksFragment : BaseFragment() {


    override fun layoutId(): Int = R.layout.fragment_tasks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}