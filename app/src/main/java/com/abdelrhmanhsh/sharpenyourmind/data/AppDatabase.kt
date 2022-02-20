package com.abdelrhmanhsh.sharpenyourmind.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Score::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getScoreDao(): ScoresDao

    companion object{
        val DATABASE_NAME: String = "app_db"
    }

}
