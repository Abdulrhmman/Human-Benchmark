package com.abdelrahmman.humanbenchmark.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScoresDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(scores: Scores): Long

    @Query("SELECT * FROM scores")
    fun getScores(): LiveData<List<Scores>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(scores: List<Scores>)

    @Query("DELETE FROM scores")
    suspend fun deleteAll()

}