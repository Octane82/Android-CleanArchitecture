package com.everlapp.cleanarch.features.tasks.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.everlapp.cleanarch.features.tasks.dto.TaskData

@Dao
interface TaskDataDao {

    @Query("SELECT * FROM taskData")
    fun getAll() : LiveData<List<TaskData>>

    @Insert(onConflict = REPLACE)
    fun insert(taskData: TaskData) : Long

    @Query("DELETE from taskData")
    fun deleteAll()

}