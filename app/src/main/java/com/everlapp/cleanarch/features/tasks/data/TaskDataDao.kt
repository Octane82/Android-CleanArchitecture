package com.everlapp.cleanarch.features.tasks.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.everlapp.cleanarch.features.tasks.dto.TaskData

@Dao
interface TaskDataDao {

    @Query("SELECT * FROM taskData")
    fun getAll() : List<TaskData>

    @Insert(onConflict = REPLACE)
    fun insert(taskData: TaskData)

    @Query("DELETE from taskData")
    fun deleteAll()

}