package com.abdelrahmman.humanbenchmark.data

import androidx.lifecycle.LiveData
import javax.inject.Inject

class ScoresRepository
@Inject
constructor(
    private val scoresDao: ScoresDao
    ) {

    val scores: LiveData<List<Scores>> = scoresDao.getScores()

    suspend fun insert(scores: Scores){
        scoresDao.insert(scores)
    }

    suspend fun delete(scores: Scores){
        scoresDao.delete(scores)
    }

    suspend fun deleteAll(){
        scoresDao.deleteAll()
    }

}
