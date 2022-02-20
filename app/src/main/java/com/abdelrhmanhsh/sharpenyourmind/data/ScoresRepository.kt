package com.abdelrhmanhsh.sharpenyourmind.data

import androidx.lifecycle.LiveData
import javax.inject.Inject

class ScoresRepository
@Inject
constructor(
    private val scoresDao: ScoresDao
    ) {

    val scores: LiveData<List<Score>> = scoresDao.getScores()

    suspend fun insert(scores: Score){
        scoresDao.insert(scores)
    }

    suspend fun delete(scores: Score){
        scoresDao.delete(scores)
    }

    suspend fun deleteAll(){
        scoresDao.deleteAll()
    }

}
