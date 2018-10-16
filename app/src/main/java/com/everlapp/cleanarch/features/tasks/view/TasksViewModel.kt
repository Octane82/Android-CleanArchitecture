package com.everlapp.cleanarch.features.tasks.view

import android.arch.lifecycle.MutableLiveData
import com.everlapp.cleanarch.core.interactor.UseCase
import com.everlapp.cleanarch.core.platform.BaseViewModel
import com.everlapp.cleanarch.features.tasks.GetTasks
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import timber.log.Timber
import javax.inject.Inject

class TasksViewModel
@Inject constructor(private val getTasks: GetTasks) : BaseViewModel() {

    var tasks: MutableLiveData<List<TaskData>> = MutableLiveData()

    fun loadTasks() = getTasks(UseCase.None()) { it.either(::handleFailure, :: handleTasksList) }

    private fun handleTasksList(tasks: List<TaskData>) {
        this.tasks.value = tasks.map { TaskData(it.id, it.name, it.createdAt) }
    }


    fun addNewTaskToDb() {
        Timber.d("Add new task to Db")
    }

}