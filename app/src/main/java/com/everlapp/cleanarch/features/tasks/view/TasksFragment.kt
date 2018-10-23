package com.everlapp.cleanarch.features.tasks.view

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.everlapp.cleanarch.R
import com.everlapp.cleanarch.core.exception.Failure
import com.everlapp.cleanarch.core.extension.failure
import com.everlapp.cleanarch.core.extension.hideKeyboard
import com.everlapp.cleanarch.core.extension.showAlertDialog
import com.everlapp.cleanarch.core.extension.viewModel
import com.everlapp.cleanarch.core.navigation.Navigator
import com.everlapp.cleanarch.core.platform.BaseFragment
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import com.everlapp.cleanarch.features.tasks.view.adapters.TasksListAdapter
import kotlinx.android.synthetic.main.fragment_tasks.*
import timber.log.Timber
import javax.inject.Inject

class TasksFragment : BaseFragment() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var tasksListAdapter: TasksListAdapter


    private lateinit var tasksViewModel: TasksViewModel


    override fun layoutId(): Int = R.layout.fragment_tasks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        Timber.d("onCreate -> TasksFragment")

        navigator.init(activity)

        // Init View model
        tasksViewModel = viewModel(viewModelFactory) {
            //observe(tasks, ::renderTaskList)
            failure(failure, ::handleFailure)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()

        tasksViewModel.loadTasks(object : TasksViewModel.DataLoadListener{
            override fun onLoadData(data: LiveData<List<TaskData>>) {
                data.observe(this@TasksFragment, Observer {
                    Timber.d("Observer DATA in FRAGMENT:::: ${it?.size}")
                    renderTaskList(it)
                })
            }
        })
    }


    private fun initializeView() {
        tasksList.layoutManager = LinearLayoutManager(activity)
        tasksList.adapter = tasksListAdapter

        // Show task detailed screen
        tasksListAdapter.clickListener = { taskData, navigationExtras ->
            navigator.showTaskDetail(activity!!, taskData, navigationExtras) }

        // Add new Task
        btnSend.setOnClickListener {
            tasksViewModel.addNewTaskToDb(etMessage.text.toString())
            etMessage.setText("")
            hideKeyboard()
        }

        // todo: AlertDialog btnSend.setOnClickListener { activity?.showAlertDialog("Its a simple ALERT", "YAY") }
    }



    private fun renderTaskList(tasks: List<TaskData>?) {
        Timber.d("Render tasks list SIZE: ${tasks?.size}")
        tasksListAdapter.collection = tasks.orEmpty()
    }


    private fun handleFailure(failure: Failure?) {
        Timber.d("Render Failure is exists: $failure")
        when (failure) {
            is Failure.DatabaseError -> renderFailure(R.string.failure_database_error)
        }
    }


    private fun renderFailure(@StringRes message: Int) {
        notify(message)
    }


}