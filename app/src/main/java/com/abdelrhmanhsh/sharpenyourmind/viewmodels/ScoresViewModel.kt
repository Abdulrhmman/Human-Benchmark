package com.abdelrhmanhsh.sharpenyourmind.viewmodels

import androidx.lifecycle.*
import com.abdelrhmanhsh.sharpenyourmind.data.Score
import com.abdelrhmanhsh.sharpenyourmind.data.ScoresRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScoresViewModel
@Inject constructor(
    private val repository: ScoresRepository,
): ViewModel() {

    val allScores: LiveData<List<Score>>

    fun insert(scores: Score) = viewModelScope.launch {
        repository.insert(scores)
    }

    fun delete(scores: Score) = viewModelScope.launch {
        repository.delete(scores)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    init {
        allScores = repository.scores
    }

}
