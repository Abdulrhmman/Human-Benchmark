package com.abdelrhmanhsh.sharpenyourmind.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ScoresDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(scores: Score): Long

    @Query("SELECT * FROM scores")
    fun getScores(): LiveData<List<Score>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(scores: List<Score>)

    @Delete
    suspend fun delete(scores: Score)

    @Query("DELETE FROM scores")
    suspend fun deleteAll()

}