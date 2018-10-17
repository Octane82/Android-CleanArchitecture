package com.everlapp.cleanarch.features.tasks.view

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.everlapp.cleanarch.core.interactor.UseCase
import com.everlapp.cleanarch.core.platform.BaseViewModel
import com.everlapp.cleanarch.features.tasks.domain.AddTask
import com.everlapp.cleanarch.features.tasks.domain.GetTasks
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import timber.log.Timber
import javax.inject.Inject


class TasksViewModel
@Inject constructor(private val getTasks: GetTasks,
                    private val addTask: AddTask) : BaseViewModel() {

    var tasks: MutableLiveData<List<TaskData>> = MutableLiveData()


    fun loadTasks() = getTasks(UseCase.None()) { it.either(::handleFailure, ::handleTasksList) }

    private fun handleTasksList(tasks: LiveData<List<TaskData>>) {
        Timber.d("HANDLE task list: ${tasks.value?.size}")
        this.tasks.value = tasks.value?.map { TaskData(it.id, it.name, it.createdAt) }


        // OLD !!!!
        //this.tasks.value = tasks.map { TaskData(it.id, it.name, it.createdAt) }
    }


    fun addNewTaskToDb(message: String) {
        val task = TaskData()
        task.name = message

        addTask(task)
    }


    // ---- TODO: Test !!!!!!!!!!! Ok its WORk !!!!!!

    fun loadTSK(lifecycle: LifecycleOwner) = getTasks(UseCase.None()) { it.either(::handleFailure) { handleYay(lifecycle, it) } }

    private fun handleYay(lifecycle: LifecycleOwner,  tasks: LiveData<List<TaskData>>) {
        tasks.observe(lifecycle, Observer { list -> kotlin.run {
            Timber.d("Obsssssssssssssssss: ${list?.size}")
        } })




    }

}