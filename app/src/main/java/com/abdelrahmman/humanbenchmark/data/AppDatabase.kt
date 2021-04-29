package com.abdelrahmman.humanbenchmark.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Scores::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getScoreDao(): ScoresDao

    companion object{
        val DATABASE_NAME: String = "app_db"
    }

}
