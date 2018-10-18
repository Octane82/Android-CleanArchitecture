package com.everlapp.cleanarch.features.tasks.view

import android.arch.lifecycle.LiveData
import com.everlapp.cleanarch.core.interactor.UseCase
import com.everlapp.cleanarch.core.platform.BaseViewModel
import com.everlapp.cleanarch.features.tasks.domain.AddTask
import com.everlapp.cleanarch.features.tasks.domain.GetTasks
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import javax.inject.Inject


class TasksViewModel
@Inject constructor(private val getTasks: GetTasks,
                    private val addTask: AddTask) : BaseViewModel() {


    interface DataLoadListener {
        fun onLoadData(data: LiveData<List<TaskData>>)
    }

    fun loadTasks(dataLoadListener: DataLoadListener) =
            getTasks(UseCase.None()) { it -> it.either(
                    ::handleFailure)
                    { handleTaskList(it, dataLoadListener) } }


    private fun handleTaskList(tasks: LiveData<List<TaskData>>,
                               dataLoadListener: DataLoadListener)  {
        dataLoadListener.onLoadData(tasks)
    }



    fun addNewTaskToDb(message: String) {
        val task = TaskData()
        task.name = message
        addTask(task)
    }






    // TODO: Experimental observer - Need Map LiveData to MutableLiveData

    //var tasks: MutableLiveData<List<TaskData>> = MutableLiveData()

    //fun loadTasks() = getTasks(UseCase.None()) { it.either(::handleFailure, ::handleTasksList) }

    /*private fun handleTasksList(tasks: LiveData<List<TaskData>>) {
        Timber.d("HANDLE task list: ${tasks.value?.size}")
        //this.tasks.value = tasks.value?.map { TaskData(it.id, it.name, it.createdAt) }


        // OLD !!!!
        // this.tasks.value = tasks.map { TaskData(it.id, it.name, it.createdAt) }
    }*/





    // ---- TODO: Test !!!!!!!!!!! Ok its WORk !!!!!!



}