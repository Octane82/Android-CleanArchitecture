package com.everlapp.cleanarch.features.tasks.data

import android.arch.lifecycle.LiveData
import com.everlapp.cleanarch.core.exception.Failure
import com.everlapp.cleanarch.core.functional.Either
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import timber.log.Timber
import javax.inject.Inject

/**
 * Get tasks data
 */
interface TasksRepository {

    /**
     * Get all tasks from Db
     */
    fun tasks() : Either<Failure, LiveData<List<TaskData>>>

    /**
     * Add single task to Db
     */
    fun addTask(task: TaskData) : Either<Failure, Boolean>


    /**
     * Database storage
     */
    class Database
    @Inject constructor (private val localDataStore: TasksLocalDataStore) : TasksRepository {


        override fun tasks(): Either<Failure, LiveData<List<TaskData>>> {
            val tasks = localDataStore.getAll()
            tasks?.let {
                return Either.Right(tasks)
            }
            return Either.Left(Failure.DatabaseError())
        }


        override fun addTask(task: TaskData): Either<Failure, Boolean> {
            Timber.d("Add TASK in TaskRepository YAY $task")
            val result = localDataStore.addTask(task)
            result?.let {
                if (result > 0) {
                    return Either.Right(true)
                }
            }
            return Either.Left(Failure.DatabaseError())
        }
    }

}