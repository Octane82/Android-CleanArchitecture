package com.everlapp.cleanarch.core.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.everlapp.cleanarch.features.tasks.data.TaskDataDao
import com.everlapp.cleanarch.features.tasks.dto.TaskData


// TODO: https://medium.com/mindorks/android-architecture-components-room-and-kotlin-f7b725c8d1d
@Database(entities = arrayOf(TaskData::class), version = 1)
abstract class DbRoom : RoomDatabase() {

    companion object {

        private var INSTANCE: DbRoom? = null

        fun getInstance(context: Context) : DbRoom? {
            if (INSTANCE == null) {
                synchronized(DbRoom::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            DbRoom::class.java,
                            "app.db")
                            .build()
                }
            }
            return INSTANCE
        }


        fun destroyInastance() {
            INSTANCE = null
        }

    }


    abstract fun taskDataDao() : TaskDataDao


}