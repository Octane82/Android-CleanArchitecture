package com.everlapp.cleanarch.features.tasks.domain

import com.everlapp.cleanarch.core.exception.Failure
import com.everlapp.cleanarch.core.functional.Either
import com.everlapp.cleanarch.core.interactor.UseCase
import com.everlapp.cleanarch.features.tasks.data.TasksRepository
import com.everlapp.cleanarch.features.tasks.dto.TaskData
import javax.inject.Inject

class GetTasks
@Inject constructor(private val tasksRepository: TasksRepository) : UseCase<List<TaskData>, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, List<TaskData>> = tasksRepository.tasks()

}