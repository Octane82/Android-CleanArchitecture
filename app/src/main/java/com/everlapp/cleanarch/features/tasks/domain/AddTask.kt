package com.everlapp.cleanarch.features.tasks.domain

import com.everlapp.cleanarch.core.exception.Failure
import com.everlapp.cleanarch.core.functional.Either
import com.everlapp.cleanarch.core.interactor.UseCase
import com.everlapp.cleanarch.features.tasks.data.TasksRepository
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import javax.inject.Inject

class AddTask
@Inject constructor(private val taskRepository: TasksRepository) : UseCase<Boolean, TaskData>() {

    /**
     * Insert new task to Db in worker thread
     */
    override suspend fun run(params: TaskData): Either<Failure, Boolean> =
            taskRepository.addTask(params)

}