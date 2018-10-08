package com.everlapp.cleanarch.features.tasks.dto

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "taskData")
data class TaskData(

        @PrimaryKey(autoGenerate = true)
        var id: Long?,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "timestamp")
        var createdAt: Long

)