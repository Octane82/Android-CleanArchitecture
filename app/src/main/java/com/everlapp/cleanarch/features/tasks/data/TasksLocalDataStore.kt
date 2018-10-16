package com.everlapp.cleanarch.features.tasks.data

import com.everlapp.cleanarch.core.data.db.DbRoom
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksLocalDataStore @Inject constructor(private val dbRoom: DbRoom?) {

    fun getAll() : List<TaskData>? =
            dbRoom?.taskDataDao()?.getAll()



    fun addTask(taskData: TaskData) : Long? =
            dbRoom?.taskDataDao()?.insert(taskData)

}