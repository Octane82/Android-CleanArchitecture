package com.everlapp.cleanarch.features.tasks.data

import com.everlapp.cleanarch.core.exception.Failure
import com.everlapp.cleanarch.core.functional.Either
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import javax.inject.Inject

/**
 * Get tasks data
 */
interface TasksRepository {

    fun tasks() : Either<Failure, List<TaskData>>




    class Database @Inject constructor (private val localDataStore: TasksLocalDataStore) : TasksRepository {


        override fun tasks(): Either<Failure, List<TaskData>> {

            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            // localDataStore.getAll()
        }


    }

}