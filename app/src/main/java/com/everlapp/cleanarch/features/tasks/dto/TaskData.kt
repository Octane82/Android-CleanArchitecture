package com.everlapp.cleanarch.features.tasks.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskData")
data class TaskData(

        @PrimaryKey(autoGenerate = true)
        var id: Long?,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "timestamp")
        var createdAt: Long

) { constructor(): this(null, "", System.currentTimeMillis()) }