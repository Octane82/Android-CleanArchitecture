package com.everlapp.cleanarch.features.tasks.view

import android.os.Bundle
import android.view.View
import com.everlapp.cleanarch.R
import com.everlapp.cleanarch.core.exception.Failure
import com.everlapp.cleanarch.core.extension.failure
import com.everlapp.cleanarch.core.extension.observe
import com.everlapp.cleanarch.core.extension.viewModel
import com.everlapp.cleanarch.core.platform.BaseFragment
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import timber.log.Timber

class TasksFragment : BaseFragment() {


    private lateinit var tasksViewModel: TasksViewModel


    override fun layoutId(): Int = R.layout.fragment_tasks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        Timber.d("onCreate -> TasksFragment")

        tasksViewModel = viewModel(viewModelFactory) {
            observe(tasks, ::renderTaskList)
            failure(failure, ::handleFailure)
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasksViewModel.loadTasks()
    }




    private fun renderTaskList(tasks: List<TaskData>?) {
        Timber.d("Render tasks list SIZE: ${tasks?.size}")
    }


    private fun handleFailure(failure: Failure?) {
        Timber.d("Render Failure is exists: $failure")
    }

}